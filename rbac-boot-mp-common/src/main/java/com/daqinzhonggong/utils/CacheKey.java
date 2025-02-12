package com.daqinzhonggong.utils;

/**
 * 这是一个Java接口，用于定义与缓存相关的键（Key）的集合
 *
 * @author free
 */
public interface CacheKey {

    // USER_ID: "user::id:"，代表用户ID的缓存键前缀
    String USER_ID = "user::id:";
    // DATA_USER: "data::user:"，代表与用户相关的数据的缓存键前缀
    String DATA_USER = "data::user:";
    // MENU_ID: "menu::id:"，代表菜单ID的缓存键前缀
    String MENU_ID = "menu::id:";
    // MENU_USER: "menu::user:"，与用户关联的菜单信息的缓存键前缀
    String MENU_USER = "menu::user:";
    // ROLE_AUTH: "role::auth:"，代表角色授权的缓存键前缀
    String ROLE_AUTH = "role::auth:";
    // ROLE_ID: "role::id:"，代表角色ID的缓存键前缀
    String ROLE_ID = "role::id:";
    // DEPT_ID: "dept::id:"，代表部门ID的缓存键前缀
    String DEPT_ID = "dept::id:";
    // JOB_ID: "job::id:"，代表岗位ID的缓存键前缀
    String JOB_ID = "job::id:";
    // DICT_NAME: "dict::name:"，代表数据字典名称的缓存键前缀
    String DICT_NAME = "dict::name:";

}
