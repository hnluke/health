package com.util;

import com.dao.*;
import com.model.pojo.*;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
@Component
public class ExcelPlug {
    @Resource
    private Menus menus;
    @Resource
    private MenusMapper menusMapper;
    @Resource
    private PriorityMapper priorityMapper;
    @Resource
    private ItemTypeMapper itemTypeMapper;
    @Resource
    private AssociationMapper associationMapper;
    @Resource
    private ItemMapper itemMapper;
    @Resource
    private SubItemMapper subItemMapper;

    /**
     * 导入Excel数据到Menu表  (1)
     * @param path
     * @return
     */
    public boolean importMenuExcelToDB(String path) {
        boolean flag = false;
        List<Menus> menusList = new ArrayList<Menus>();
        menusList = getMenuAllByExcel(path);
        for (Menus menu : menusList) {
            if (menusMapper.findMenuCount(menu.getMenuName()) < 1) {
                menusMapper.insertMenus(menu);
            } else {
                menusMapper.updateMenus(menu);
            }
        }
        flag = true;
        return flag;
    }


    /**
     * 将Excel的数据转化为菜单pojo对象的List
     *
     * @param path 文件路径
     * @return
     */
    public List<Menus> getMenuAllByExcel(String path) {
        List<Menus> list = new ArrayList<Menus>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(path));
            Sheet rs = rwb.getSheet("菜单");// 或者rwb.getSheet(0)
            int clos = rs.getColumns();// 得到所有的列
            int rows = rs.getRows();// 得到所有的行
            System.out.println(clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    // 第一个是列数，第二个是行数
                    //j = j + 1;
                    // 默认最左边编号也算一列
                    String menuPath = rs.getCell(j++, i).getContents();
                    Integer menuResId = Integer.parseInt(rs.getCell(j++, i).getContents());
                    Integer menuParId = Integer.parseInt(rs.getCell(j++, i).getContents());
                    Integer menuGrpId = Integer.parseInt(rs.getCell(j++, i).getContents());
                    String menuName = rs.getCell(j++, i).getContents();
                    Menus menu = new Menus();
                    menu.setMenuPath(menuPath);
                    menu.setMenuResId(menuResId);
                    menu.setMenuParId(menuParId);
                    menu.setMenuGrpId(menuGrpId);
                    menu.setMenuName(menuName);
                    list.add(menu);
                }
            }
        } catch (Exception e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 导入Excel数据到priority表  (2)
     *
     * @param path
     * @return
     */
    public boolean importPrioExcelToDB(String path) {
        boolean flag = false;
        List<Priority> prioList = new ArrayList<Priority>();
        prioList = getPrioAllByExcel(path);
        for (Priority prio : prioList) {
            if (priorityMapper.findPrioCount(prio.getPrioName()) < 1) {
                priorityMapper.insertPriority(prio);
            } else {
                priorityMapper.updatePriority(prio);
            }
        }
        flag = true;
        return flag;
    }


    /**
     * 将Excel的数据转化为权限pojo对象的List
     *
     * @param path 文件路径
     * @return
     */
    public List<Priority> getPrioAllByExcel(String path) {
        List<Priority> list = new ArrayList<Priority>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(path));
            Sheet rs = rwb.getSheet("权限");// 或者rwb.getSheet(0)
            int clos = rs.getColumns();// 得到所有的列
            int rows = rs.getRows();// 得到所有的行
            System.out.println(clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    // 第一个是列数，第二个是行数
                    //j = j + 1;
                    // 默认最左边编号也算一列
                    String prioName = rs.getCell(j++, i).getContents();
                    String prioDesc = rs.getCell(j++, i).getContents();
                    Priority prio = new Priority();
                    prio.setPrioName(prioName);
                    prio.setPrioDesc(prioDesc);

                    list.add(prio);
                }
            }
        } catch (Exception e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 导入Excel数据到项目类别表  (3)
     *
     * @param path
     * @return
     */
    public boolean importItemTypeExcelToDB(String path) {
        boolean flag = false;
        List<ItemType> itemTypeList = new ArrayList<ItemType>();
        itemTypeList = getItemTypeAllByExcel(path);
        for (ItemType itemType : itemTypeList) {
            if (itemTypeMapper.findItemType(itemType.getTypeName()).size() < 1) {
                itemTypeMapper.insertItemType(itemType.getTypeName());
            }

        }
        flag = true;
        return flag;
    }


    /**
     * 将Excel的数据转化为权限pojo对象的List
     * @param path 文件路径
     * @return
     */
    public List<ItemType> getItemTypeAllByExcel(String path) {
        List<ItemType> list = new ArrayList<ItemType>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(path));
            Sheet rs = rwb.getSheet("项目类别");// 或者rwb.getSheet(0)
            int clos = rs.getColumns();// 得到所有的列
            int rows = rs.getRows();// 得到所有的行
            System.out.println(clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    // 第一个是列数，第二个是行数
                    //j = j + 1;
                    // 默认最左边编号也算一列
                    String typeName = rs.getCell(j++, i).getContents();
                    ItemType itemType = new ItemType();
                    itemType.setTypeName(typeName);
                    list.add(itemType);
                }
            }
        } catch (Exception e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 导入Excel数据到priority表  (4)
     *
     * @param path
     * @return
     */
    public boolean importAssoExcelToDB(String path) {
        boolean flag = false;
        List<Association> assoList = new ArrayList<Association>();
        assoList = getAssoAllByExcel(path);
        for (Association asso : assoList) {
            if (associationMapper.findAssociation(asso.getAssoName()).size() < 1) {
                associationMapper.insertAssociation(asso);
            } else {
                associationMapper.updateAssociation(asso);
            }
        }
        flag = true;
        return flag;
    }


    /**
     * 将Excel的数据转化为权限pojo对象的List
     *
     * @param path 文件路径
     * @return
     */
    public List<Association> getAssoAllByExcel(String path) {
        List<Association> list = new ArrayList<Association>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(path));
            Sheet rs = rwb.getSheet("套餐");// 或者rwb.getSheet(0)
            int clos = rs.getColumns();// 得到所有的列
            int rows = rs.getRows();// 得到所有的行
           // System.out.println(clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    // 第一个是列数，第二个是行数
                    //j = j + 1;
                    // 默认最左边编号也算一列
                    String assoName = rs.getCell(j++, i).getContents();
                    double assoPrice = Double.parseDouble(rs.getCell(j++, i).getContents());
                    Association asso = new Association();
                    asso.setAssoName(assoName);
                    asso.setAssoPrice(assoPrice);

                    list.add(asso);
                }
            }
        } catch (Exception e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 导入Excel数据到项目表item  (5)
     *
     * @param path
     * @return
     */
    public boolean importItemExcelToDB(String path) {
        boolean flag = false;
        List<Item> itemList = new ArrayList<Item>();
        itemList = getItemAllByExcel(path);
        for (Item item : itemList) {
            if (itemMapper.findItem(item.getItemName()).size() < 1) {
                itemMapper.insertItem(item);
            } else {
                itemMapper.updateItem(item);
            }
        }
        flag = true;
        return flag;
    }


    /**
     * 将Excel的数据转化为项目pojo对象的List
     *
     * @param path 文件路径
     * @return
     */
    public List<Item> getItemAllByExcel(String path) {
        List<Item> list = new ArrayList<Item>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(path));
            Sheet rs = rwb.getSheet("项目");// 或者rwb.getSheet(0)
            int clos = rs.getColumns();// 得到所有的列
            int rows = rs.getRows();// 得到所有的行
            //System.out.println(clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    // 第一个是列数，第二个是行数
                    //j = j + 1;
                    // 默认最左边编号也算一列
                    String itemName = rs.getCell(j++, i).getContents();
                    String itemCode = rs.getCell(j++, i).getContents();
                    double itemPrice = Double.parseDouble(rs.getCell(j++, i).getContents());
                    Item item = new Item();
                    item.setItemName(itemName);
                    item.setItemCode(itemCode);
                    item.setItemPrice(itemPrice);
                    list.add(item);
                }
            }
        } catch (Exception e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 导入Excel数据到项目表item  (6)
     *
     * @param path
     * @return
     */
    public boolean importSubItemExcelToDB(String path) {
        boolean flag = false;
        List<SubItem> subItemList = new ArrayList<SubItem>();
        subItemList = getSubItemAllByExcel(path);
        for (SubItem subItem : subItemList) {
            if (subItemMapper.findSubItem(subItem.getSubName()).size() < 1) {
                subItemMapper.insertSubItem(subItem);
            } else {
                subItemMapper.updateSubItem(subItem);
            }
        }
        flag = true;
        return flag;
    }


    /**
     * 将Excel的数据转化为项目pojo对象的List
     *
     * @param path 文件路径
     * @return
     */
    public List<SubItem> getSubItemAllByExcel(String path) {
        List<SubItem> list = new ArrayList<SubItem>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(path));
            Sheet rs = rwb.getSheet("细项目");// 或者rwb.getSheet(0)
            int clos = rs.getColumns();// 得到所有的列
            int rows = rs.getRows();// 得到所有的行
            //System.out.println(clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    // 第一个是列数，第二个是行数
                    //j = j + 1;
                    // 默认最左边编号也算一列
                    String subName = rs.getCell(j++, i).getContents();
                    String subCode = rs.getCell(j++, i).getContents();
                    String subUnit = rs.getCell(j++, i).getContents();
                    String subRefer = rs.getCell(j++, i).getContents();
                    Integer subUpper = Integer.parseInt(rs.getCell(j++, i).getContents());
                    Integer subLower = Integer.parseInt(rs.getCell(j++, i).getContents());
                    Integer itemId = Integer.parseInt(rs.getCell(j++, i).getContents());
                    SubItem subItem = new SubItem();
                    subItem.setSubName(subName);
                    subItem.setSubCode(subCode);
                    subItem.setSubUnit(subUnit);
                    subItem.setSubRefer(subRefer);
                    subItem.setSubUpper(subUpper);
                    subItem.setSubUpper(subLower);
                    subItem.setItemId(itemId);
                    list.add(subItem);
                }
            }
        } catch (Exception e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }


}