package com.daqinzhonggong.modules.quartz.utils;

import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.daqinzhonggong.domain.vo.EmailVo;
import com.daqinzhonggong.modules.quartz.domain.QuartzJob;
import com.daqinzhonggong.modules.quartz.domain.QuartzLog;
import com.daqinzhonggong.modules.quartz.mapper.QuartzLogMapper;
import com.daqinzhonggong.modules.quartz.service.QuartzJobService;
import com.daqinzhonggong.service.EmailService;
import com.daqinzhonggong.utils.RedisUtils;
import com.daqinzhonggong.utils.SpringContextHolder;
import com.daqinzhonggong.utils.StringUtils;
import com.daqinzhonggong.utils.ThrowableUtil;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Async
public class ExecutionJob extends QuartzJobBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 此处仅供参考，可根据任务执行情况自定义线程池参数
    private final ThreadPoolTaskExecutor executor = SpringContextHolder.getBean("rbacBootMpAsync");

    @Override
    public void executeInternal(JobExecutionContext context) {
        // 获取任务
        QuartzJob quartzJob = (QuartzJob) context.getMergedJobDataMap().get(QuartzJob.JOB_KEY);
        // 获取spring bean
        QuartzLogMapper quartzLogMapper = SpringContextHolder.getBean(QuartzLogMapper.class);
        QuartzJobService quartzJobService = SpringContextHolder.getBean(QuartzJobService.class);
        RedisUtils redisUtils = SpringContextHolder.getBean(RedisUtils.class);

        String uuid = quartzJob.getUuid();

        QuartzLog log = new QuartzLog();
        log.setJobName(quartzJob.getJobName());
        log.setBeanName(quartzJob.getBeanName());
        log.setMethodName(quartzJob.getMethodName());
        log.setParams(quartzJob.getParams());
        long startTime = System.currentTimeMillis();
        log.setCronExpression(quartzJob.getCronExpression());
        try {
            // 执行任务
            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(), quartzJob.getParams());
            Future<?> future = executor.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            if (StringUtils.isNotBlank(uuid)) {
                redisUtils.set(uuid, true);
            }
            // 任务状态
            log.setIsSuccess(true);
            logger.info("任务执行成功，任务名称：" + quartzJob.getJobName() + ", 执行时间：" + times + "毫秒");
            // 判断是否存在子任务
            if (StringUtils.isNotBlank(quartzJob.getSubTask())) {
                String[] tasks = quartzJob.getSubTask().split("[,，]");
                // 执行子任务
                quartzJobService.executionSubJob(tasks);
            }
        } catch (Exception e) {
            if (StringUtils.isNotBlank(uuid)) {
                redisUtils.set(uuid, false);
            }
            logger.error("任务执行失败，任务名称：" + quartzJob.getJobName());
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            // 任务状态 0：成功 1：失败
            log.setIsSuccess(false);
            log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
            // 任务如果失败了则暂停
            if (quartzJob.getPauseAfterFailure() != null && quartzJob.getPauseAfterFailure()) {
                quartzJob.setIsPause(false);
                // 更新状态
                quartzJobService.updateIsPause(quartzJob);
            }
            if (quartzJob.getEmail() != null) {
                EmailService emailService = SpringContextHolder.getBean(EmailService.class);
                // 邮箱报警
                if (StringUtils.isNoneBlank(quartzJob.getEmail())) {
                    EmailVo emailVo = taskAlarm(quartzJob, ThrowableUtil.getStackTrace(e));
                    emailService.send(emailVo, emailService.find());
                }
            }
        } finally {
            quartzLogMapper.insert(log);
        }
    }

    private EmailVo taskAlarm(QuartzJob quartzJob, String msg) {
        EmailVo emailVo = new EmailVo();
        emailVo.setSubject("定时任务【" + quartzJob.getJobName() + "】执行失败，请尽快处理！");
        Map<String, Object> data = new HashMap<>(16);
        data.put("task", quartzJob);
        data.put("msg", msg);
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("taskAlarm.ftl");
        emailVo.setContent(template.render(data));
        List<String> emails = Arrays.asList(quartzJob.getEmail().split("[,，]"));
        emailVo.setTos(emails);
        return emailVo;
    }

}
