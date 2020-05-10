package com.service.impl;

import com.dao.*;
import com.model.pojo.*;
import com.service.IManageService;
import com.util.ExcelPlug;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManageServiceImpl implements IManageService {
    @Resource
    private CardsMapper cardsMapper;
    @Resource
    private Cards cards;
    @Resource
    private UnionQueryMapper unionQueryMapper;
    @Resource
    private PriorityMapper priorityMapper;
    @Resource
    private MenusMapper menusMapper;
    @Resource
    private ExcelPlug excelPlug;
    @Resource
    private MenuPrioMapper menuPrioMapper;
    @Resource
    private DetailMapper detailMapper;
    @Resource
    private SubItemMapper subItemMapper;
    @Resource
    private ItemMapper itemMapper;
    @Resource
    private OfficeMapper officeMapper;
    @Resource
    private ItemTypeMapper itemTypeMapper;
    @Resource
    private AssociationMapper associationMapper;
    @Resource
    private AssoItemMapper assoItemMapper;



    /**
     * 初始化卡片
     * @author Luke
     * @param cardNumber    卡的数量
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public List<Cards> initCards(int cardNumber) {
        // 查询cards表的最后一条记录
        Cards queryCards = cardsMapper.findLastCard();
        int start = 1;
        // 如果表中有记录，则卡片编号从最后开始继续
        if (queryCards != null) {
            start = Integer.parseInt(queryCards.getCardNo()) + 1;
        }
        for(int i = start; i < start + cardNumber; i++) {
            // 格式化卡片编号为8位，并且而且多余的位补0
            cards.setCardNo(String.format("%08d", i));
            cards.setCardMoney(0D);
            cardsMapper.insertCards(cards);
        }
        List<Cards> cardsList = cardsMapper.findCards("");
        return cardsList;
    }

    public List<Cards> deleteCard(Integer cardId)
    {
        List<Cards> cardsList = null;
        if(cardsMapper.deleteCards(cardId)) {
            cardsList = cardsMapper.findCards("");
        }
        return cardsList;
    }

    /**
     * 查询卡片的人员信息
     * @author Luke
     * @param cardNos   卡片编号
     * @return
     */
    public List<Cards> showPersonData(String cardNos) {
        List<Cards> cardsList = unionQueryMapper.queryCardsPerson(cardNos);
        return cardsList;
    }

    /**
     * 新增权限业务
     * @author Luke
     * @param priority 权限表pojo
     * @return
     */
    public boolean insertPriority(Priority priority) {

        return priorityMapper.insertPriority(priority);
    }


    /**
     * 删除权限
     * @author Luke
     * @param prioId 权限表pojo
     * @return
     */
    public boolean deletePriority(Integer prioId) {

        return priorityMapper.deletePriority(prioId);

    }

    /**
     * 查询权限表
     * @author Luke
     * @return
     */
    public List<Priority> findPriority(String prioName) {
        List<Priority> priorityList = null;
        priorityList = priorityMapper.findPriority("");
        return priorityList;
    }

    /**
     * 插入菜单项
     * @author Luke
     * @param menus 菜单表pojo
     * @return
     */
    @Override
    public boolean insertMenus(Menus menus) {
        return menusMapper.insertMenus(menus);
    }

    /**
     * 删除菜单项
     * @author Luke
     * @param menuId    菜单表id
     * @return
     */
    @Override
    public boolean deleteMenus(Integer menuId) {
        return menusMapper.deleteMenus(menuId);
    }

    /**
     * 查找菜单项
     * @author Luke
     * @param menuId    菜单表id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public List<Menus> findMenus(Integer menuId) {
        List<Menus> menusList = null;
        menusList = menusMapper.findMenus(menuId);
        return menusList;
    }

    /**
     * 导入菜单数据
     * @author Luke
     * @param path  Excel文件路径
     * @return
     */

    public String importMenu(String path) {
        String message = "导入数据失败";
        if (excelPlug.importMenuExcelToDB(path)) {
            message = "导入数据成功";
        }
        return message;
    }

    /**
     * 导入权限数据
     * @author Luke
     * @param path  Excel文件路径
     * @return
     */
    public String importPrio(String path) {
        String message = "导入数据失败";
        if (excelPlug.importPrioExcelToDB(path)) {
            message = "导入数据成功";
        }
        return message;
    }

    /**
     * 导入细项数据
     * @author Luke
     * @param path  Excel文件路径
     * @return
     */
    public List<SubItem> importSubItem(String path) {
        List<SubItem> subItemList = null;
        if (excelPlug.importSubItemExcelToDB(path)) {
            subItemList = subItemMapper.findSubItem("");
        }
        return subItemList;
    }

    public String importItem(String path) {
        String message = "导入失败";
        if(excelPlug.importItemExcelToDB(path)) {
            message = "导入数据成功";
        }
        return message;
    }


    /**
     * 关联权限-菜单表
     * @author Luke
     * @param prioId    权限表id
     * @param menuId    菜单表id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public boolean relatePrioMenu(Integer prioId, Integer menuId) {
        return menuPrioMapper.insertMenuPrio(prioId, menuId);
    }

    /**
     * 查询菜单-权限表(多对多)
     * @author Luke
     * @param prioName  权限名
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public List<Priority> queryPrioMenu(String prioName) {
        return unionQueryMapper.queryPrioMenu(prioName);
    }

    /**
     * 删除菜单-权限表记录
     * @author Luke
     * @param prmeId    关联表id
     * @return
     */
    @Override
    public boolean deleteMenuPrio(Integer prmeId) {

        return menuPrioMapper.deleteMenuPrio(prmeId);
    }

    /**
     * 删除细项
     * @author Luke
     * @param subId
     * @return
     */
    public List<SubItem> deleteSubItem(Integer subId) {
        List<SubItem> subItemList = null;
        if(subItemMapper.deleteSubItem(subId)) {
            subItemList = subItemMapper.findSubItem("");
        }
        return subItemList;
    }

    /**
     * 查询项目表
     * @param itemName  项目名称
     * @return
     */
    public List<Item> findItem(String itemName) {
        return itemMapper.findItem(itemName);
    }

    public List<Office> findOffice(String offName) {
        return officeMapper.findOffice(offName);
    }

    public List<ItemType> findItemType(String typeName) {
        return itemTypeMapper.findItemType(typeName);
    }

    public List<Association> findAssociation(String assoName) {
        return associationMapper.findAssociation(assoName);
    }

    /**
     * 更新项目
     * @author Luke
     * @param item
     * @return
     */
    @Override
    public boolean updateItem(Item item) {

        return itemMapper.updateItem(item);
    }

    /**
     * 插入套餐表记录
     * @author Luke
     * @param association
     * @return
     */
    public boolean insertAsso(Association association) {
        return associationMapper.insertAssociation(association);
    }

    /**
     * 插入套项表记录
     * @author Luke
     * @param assoId
     * @param itemId
     * @return
     */
    @Override
    public boolean insertAssoItem(Integer assoId, Integer itemId) {
        return assoItemMapper.insertAssoItem(assoId, itemId);
    }

    /**
     * 删除套餐-项目关联表记录
     * @author Luke
     * @param asitId
     * @return
     */
    @Override
    public boolean deleteAssoItem(Integer asitId) {
        return assoItemMapper.deleteAssoItem(asitId);
    }

    /**
     * 查询套餐-项目关联表
     * @author Luke
     * @param assoName
     * @return
     */
    public List<Association> queryAssoItem(String assoName) {
        return unionQueryMapper.queryAssoItem(assoName);
    }

    @Override
    public boolean deleteAssoItemByAssoId(Integer assoId) {
        return assoItemMapper.deleteAssoItemByAssoId(assoId);
    }

    @Override
    public boolean deleteAsso(Integer assoId) {
        return associationMapper.deleteAssociation(assoId);
    }
}
