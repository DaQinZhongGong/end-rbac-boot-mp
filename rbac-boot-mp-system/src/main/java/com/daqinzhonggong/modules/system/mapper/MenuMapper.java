package com.daqinzhonggong.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daqinzhonggong.modules.system.domain.Menu;
import com.daqinzhonggong.modules.system.domain.vo.MenuQueryCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findAll(@Param("criteria") MenuQueryCriteria criteria);

    LinkedHashSet<Menu> findByRoleIdsAndTypeNot(@Param("roleIds") Set<Long> roleIds, @Param("type") Integer type);

    List<Menu> findByPidIsNullOrderByMenuSort();

    List<Menu> findByPidOrderByMenuSort(@Param("pid") Long pid);

    @Select("SELECT menu_id id FROM sys_menu WHERE title = #{title}")
    Menu findByTitle(@Param("title") String title);

    @Select("SELECT menu_id id FROM sys_menu WHERE name = #{name}")
    Menu findByComponentName(@Param("name") String name);

    @Select("SELECT count(*) FROM sys_menu WHERE pid = #{pid}")
    int countByPid(@Param("pid") Long pid);

    @Select("update sys_menu set sub_count = #{count} where menu_id = #{menuId} ")
    void updateSubCntById(@Param("count") int count, @Param("menuId") Long menuId);

}
