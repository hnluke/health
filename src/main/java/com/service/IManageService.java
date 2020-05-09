package com.service;

import com.model.pojo.Cards;
import com.model.pojo.MenuPrio;
import com.model.pojo.Menus;
import com.model.pojo.Priority;

import java.util.List;

public interface IManageService {
    /**
     * 初始化体检卡
     * @param cardNumber    卡的数量
     * @return
     */
    public List<Cards> initCards(int cardNumber);

    /**
     * 删除卡片
     * @param cardId
     * @return
     */
    public List<Cards> deleteCard(Integer cardId);

    /**
     * 获取卡片信息
     * @param cardNos
     * @return
     */
    public List<Cards> showPersonData(String cardNos);

    /**
     * 新增权限业务
     * @param priority 权限表pojo
     * @return
     */
    public boolean insertPriority(Priority priority);

    /**
     * 删除权限业务
     * @param prioId 权限表pojo
     * @return
     */
    public boolean deletePriority(Integer prioId);

    /**
     * 查询权限表
     * @return
     */
    public List<Priority> findPriority(String prioName);

    /**
     * 插入菜单项
     * @param menus 菜单表pojo
     * @return
     */
    public boolean insertMenus(Menus menus);


    /**
     * 删除菜单项
     * @param menuId    菜单表id
     * @return
     */
    public boolean deleteMenus(Integer menuId);

    /**
     * 查询菜单表
     * @param menuId    菜单表id
     * @return
     */
    public List<Menus> findMenus(Integer menuId);

    /**
     * 导入菜单数据
     * @param path
     * @return
     */
    public String importMenu(String path);

    /**导入权限数据
     * @param path
     * @return
     */
    public String importPrio(String path);

    /**
     * 关联权限-菜单表
     * @param prioId
     * @param prioId
     * @return
     */
    public boolean relatePrioMenu(Integer prioId, Integer menuId);

    /**
     * 查询权限-菜单关联表
     * @param prioName
     * @return
     */
    public List<Priority> queryPrioMenu(String prioName);

    /**
     * 删除菜单-权限关联表记录
     * @param prmeId    关联表id
     * @return
     */
    public boolean deleteMenuPrio(Integer prmeId);
}
