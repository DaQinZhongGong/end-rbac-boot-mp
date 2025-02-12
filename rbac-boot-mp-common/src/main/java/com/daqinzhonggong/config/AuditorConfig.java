package com.daqinzhonggong.config;

import com.daqinzhonggong.utils.SecurityUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 设置审计
 * 实现了Spring Data JPA的AuditorAware接口，用于在启用审计功能时提供当前操作员的信息
 *
 * @author free
 */
// @Component("auditorAware")：这个注解表明AuditorConfig是一个Spring组件，并且它会被注册到Spring容器中，注册的Bean名称为auditorAware。这样，Spring Data JPA就可以通过名称自动检测并使用这个Bean来获取当前操作员的信息。
@Component("auditorAware")
// AuditorConfig类实现了AuditorAware<String>接口。AuditorAware接口是Spring Data JPA用于审计功能的一部分，它允许开发者在保存或更新实体时自动填充与操作员相关的信息（如创建人、最后修改人等）。<String>泛型表示操作员信息将以字符串形式提供。
public class AuditorConfig implements AuditorAware<String> {

    /**
     * 返回操作员标志信息
     *
     * @return /
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            // 这里应根据实际业务情况获取具体信息:在方法内部，首先尝试通过调用SecurityUtils.getCurrentUsername()方法来获取当前操作员的用户名。
            // 如果获取用户名成功，则使用Optional.of(username)将其封装为一个Optional对象并返回。Optional是Java 8引入的一个容器类，用于表示一个值存在或不存在。
            return Optional.of(SecurityUtils.getCurrentUsername());
        } catch (Exception ignored) {
            // 如果在获取用户名过程中发生异常（例如，当前请求没有经过身份验证），则捕获异常（使用catch (Exception ignored)）并返回一个默认的操作员信息"System"，同样使用Optional.of("System")封装。
        }
        // 用户定时任务，或者无Token调用的情况
        return Optional.of("System");
    }

}
