package com.daqinzhonggong.aspect;

import com.daqinzhonggong.annotation.Limit;
import com.daqinzhonggong.exception.BadRequestException;
import com.daqinzhonggong.utils.RequestHolder;
import com.daqinzhonggong.utils.StringUtils;
import com.google.common.collect.ImmutableList;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * LimitAspect类通过Spring AOP和Redis实现了对方法访问频率的限制。它通过拦截标注了@Limit注解的方法，并根据注解中的配置来检查请求是否超出了访问限制。如果请求超出了限制，则会抛出异常并阻止请求的进一步处理。
 *
 * @author free
 */
// @Aspect：这个注解表明LimitAspect是一个切面类，它定义了一个或多个切面（Advice），这些切面可以在方法执行的不同阶段被织入（Weaving）到目标对象的方法中。
@Aspect
// @Component：这个注解表明LimitAspect是一个Spring组件，Spring容器会自动检测并注册这个类的实例。
@Component
public class LimitAspect {

    // RedisTemplate<Object,Object> redisTemplate：这是Spring Data Redis提供的模板类，用于在Java应用程序中执行Redis操作。LimitAspect使用这个模板来存储和检查访问计数。
    private final RedisTemplate<Object, Object> redisTemplate;
    // Logger logger：用于记录日志，帮助开发者了解切面的执行情况。
    private static final Logger logger = LoggerFactory.getLogger(LimitAspect.class);

    // LimitAspect(RedisTemplate<Object,Object> redisTemplate)：构造函数接收一个RedisTemplate对象作为参数，并将其赋值给成员变量。这样，LimitAspect就可以使用Redis来存储和检查访问计数了。
    public LimitAspect(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 切点定义 @Pointcut("@annotation(com.daqinzhonggong.annotation.Limit)")：这个注解定义了一个切点，它匹配所有标注了@Limit注解的方法。这样，LimitAspect就可以对这些方法进行拦截了。
    @Pointcut("@annotation(com.daqinzhonggong.annotation.Limit)")
    public void pointcut() {
    }

    // 环绕通知 @Around("pointcut()")：这个注解定义了一个环绕通知，它会在目标方法执行前后被织入。环绕通知可以决定是否继续执行目标方法，以及如何处理目标方法的返回值和异常。
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // around(ProceedingJoinPoint joinPoint)方法：这是环绕通知的实现方法。它首先获取HTTP请求、方法签名和@Limit注解的配置信息。然后，根据注解中的key和limitType属性来确定用于存储访问计数的Redis键。接着，它使用Lua脚本在Redis中执行计数操作，并根据计数结果决定是否允许请求继续执行。如果请求超出了访问限制，则会抛出BadRequestException异常。
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method signatureMethod = signature.getMethod();
        Limit limit = signatureMethod.getAnnotation(Limit.class);
        LimitType limitType = limit.limitType();
        String key = limit.key();
        if (StringUtils.isEmpty(key)) {
            if (limitType == LimitType.IP) {
                key = StringUtils.getIp(request);
            } else {
                key = signatureMethod.getName();
            }
        }

        ImmutableList<Object> keys = ImmutableList.of(StringUtils.join(limit.prefix(), "_", key, "_", request.getRequestURI().replace("/", "_")));

        String luaScript = buildLuaScript();
        RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
        Number count = redisTemplate.execute(redisScript, keys, limit.count(), limit.period());
        if (null != count && count.intValue() <= limit.count()) {
            logger.info("第{}次访问key为 {}，描述为 [{}] 的接口", count, keys, limit.name());
            return joinPoint.proceed();
        } else {
            throw new BadRequestException("访问次数受限制");
        }
    }

    /**
     * 限流脚本
     * buildLuaScript()方法：这个方法构建了一个Lua脚本，用于在Redis中执行计数操作。Lua脚本是一种轻量级的脚本语言，它可以在Redis中直接执行，从而避免多次网络往返带来的性能开销。这个脚本首先尝试获取Redis键的值（即访问计数），然后判断计数是否超出了限制。如果没有超出限制，脚本会递增计数并设置过期时间；如果超出了限制，脚本会返回当前计数。
     */
    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }

}
