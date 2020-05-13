package com.service;

import com.model.pojo.*;

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

    /**
     * 导入细项数据
     * @param path
     * @return
     */
    public List<SubItem> importSubItem(String path);

    /**
     * 删除细项
     * @param subId
     * @return
     */
    public List<SubItem> deleteSubItem(Integer subId);

    /**
     * 查询项目表
     * @param itemName  项目名称
     * @return
     */
    public List<Item> findItem(String itemName);

    /**
     * 查询科室表
     * @param offName
     * @return
     */
    public List<Office> findOffice(String offName);

    /**
     * 查询项目类别表
     * @param typeName
     * @return
     */
    public List<ItemType> findItemType(String typeName);

    /**
     * 更新项目表
     * @param item
     * @return
     */
    public boolean updateItem(Item item);

    /**
     * Excel导入项目
     * @param path
     * @return
     */
    public String importItem(String path);

    /**
     * 查询套餐表
     * @param assoName
     * @return
     */
    public List<Association> findAssociation(String assoName);

    /**
     * 插入套餐记录
     * @param association
     * @return
     */
    public boolean insertAsso(Association association);

    /**
     * 插入套餐-项目关联表记录
     * @param assoId
     * @param itemId
     * @return
     */
    public boolean insertAssoItem(Integer assoId, Integer itemId);

    /**
     * 删除关联表记录
     * @param asitId
     * @return
     */
    public boolean deleteAssoItem(Integer asitId);

    /**
     * 查询套餐-项目关联表
     * @param assoName
     * @return
     */
    public List<Association> queryAssoItem(String assoName);

    /**
     * 删除指定的套餐
     * @param assoId
     * @return
     */
    public boolean deleteAssoItemByAssoId(Integer assoId);

    /**
     * 删除套餐表记录
     * @param assoId
     * @return
     */
    public boolean deleteAsso(Integer assoId);

    /**
     * 查询用户-科室-权限关联数据
     * @param userId
     * @return
     */
    public List<Users> queryUserOffPrio(Integer userId);

}
