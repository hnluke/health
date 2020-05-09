package com.util;

import com.dao.MenusMapper;
import com.dao.PriorityMapper;
import com.model.pojo.Menus;
import com.model.pojo.Priority;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
@Component
public class ExcelPlugMenuPrio {
    @Resource
    private Menus menus;
    @Resource
    private MenusMapper menusMapper;
    @Resource
    private PriorityMapper priorityMapper;
    public boolean importMenuExcelToDB(String path) {
        boolean flag = false;
        List<Menus> menusList = new ArrayList<Menus>();
        menusList = getMenuAllByExcel(path);
        for(Menus menu : menusList) {
            if(menusMapper.findMenuCount(menu.getMenuName()) < 1) {
                menusMapper.insertMenus(menu);
            }else {
                menusMapper.updateMenus(menu);
            }
        }
        flag = true;
        return flag;
    }


    /**
     * 将Excel的数据转化为pojo对象的List
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


    public boolean importPrioExcelToDB(String path) {
        boolean flag = false;
        List<Priority> prioList = new ArrayList<Priority>();
        prioList = getPrioAllByExcel(path);
        for(Priority prio : prioList) {
            if(priorityMapper.findPrioCount(prio.getPrioName()) < 1) {
                priorityMapper.insertPriority(prio);
            }else {
                priorityMapper.updatePriority(prio);
            }
        }
        flag = true;
        return flag;
    }


    /**
     * 将Excel的数据转化为pojo对象的List
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
}