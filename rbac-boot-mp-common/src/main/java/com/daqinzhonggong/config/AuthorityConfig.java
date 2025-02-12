package com.daqinzhonggong.config;

import com.daqinzhonggong.utils.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 这个类的主要功能是提供权限检查的方法
 *
 * @author free
 */
// @Service(value = "el")：这个注解表明AuthorityConfig是一个服务组件，它会被Spring容器管理。value = "el"指定了这个服务组件在Spring容器中的名称，即可以通过@Autowired注解并使用@Qualifier("el")来注入这个Bean。
@Service(value = "el")
public class AuthorityConfig {

    // check(String ...permissions)：这是一个公开的方法，接收可变数量的String类型参数permissions，这些参数代表了需要检查的权限。方法的返回类型是Boolean，表示当前用户是否拥有指定的权限之一或者是否拥有"admin"权限。
    public Boolean check(String... permissions) {
        // 获取当前用户的所有权限：通过调用SecurityUtils.getCurrentUser().getAuthorities()获取当前用户的所有权限，其中getAuthorities()方法返回的是一个GrantedAuthority的集合。然后使用Java 8的流操作（stream()）和map函数将每个GrantedAuthority对象转换为其权限字符串（通过getAuthority()方法），最后使用collect(Collectors.toList())收集这些权限字符串到一个列表中，命名为elPermissions。
        List<String> elPermissions = SecurityUtils.getCurrentUser().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        // 检查权限：方法接下来检查elPermissions列表中是否包含字符串"admin"，或者permissions参数中的任意一个权限是否存在于elPermissions列表中。这是通过elPermissions.contains("admin")和Arrays.stream(permissions).anyMatch(elPermissions::contains)两个条件实现的。如果这两个条件中的任何一个为真，则方法返回true，表示当前用户拥有足够的权限；否则返回false。判断当前用户的所有权限是否包含接口上定义的权限
        return elPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(elPermissions::contains);
    }

}
