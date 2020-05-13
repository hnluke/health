package com.dao;

import com.model.pojo.Menus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenusMapper {

    /**
     * 新增菜单
     * @param menus 菜单pojo
     * @return
     */
    public boolean insertMenus(Menus menus);

    /**
     * 查询菜单表
     * @param menuId 菜单表id
     * @return
     */
    public List<Menus> findMenus(@Param("menuId") Integer menuId);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    public boolean deleteMenus(@Param("menuId") Integer menuId);

    /**
     * 更新菜单
     * @param menus 菜单pojo
     * @return
     */
    public boolean updateMenus(Menus menus);

    public Integer findMenuCount(@Param("menuName") String menuName);
}
