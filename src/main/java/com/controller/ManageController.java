package com.controller;

import com.model.pojo.*;
import com.service.IManageService;
import com.service.IUsersService;
import com.service.impl.ManageServiceImpl;
import com.sun.deploy.net.HttpResponse;
import com.util.ExcelPlug;
import org.jboss.logging.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @Resource
    private IManageService manageService;
    @Resource
    private IUsersService usersService;
    @Resource
    private ExcelPlug excelPlug;
    private String  path = ManageController.class.getClassLoader().getResource("common/resc.xls").getPath();

    /**
     * 初始化卡片
     * @author Luke
     * @param id
     * @param cardNumber
     * @param cardId
     * @return
     */
    @RequestMapping("/initCard/{id}")
    public ModelAndView initCard(@PathVariable("id") Integer id, String cardNumber, Integer cardId) {
        ModelAndView modelAndView = new ModelAndView();
        List<Cards> cardsList = null;
        if(id == 2) {
            int cardNum = 0;
            try {
                cardNum = Integer.parseInt(cardNumber);
            } catch (NumberFormatException e) {
                cardNum = 0;
            }
            cardsList = manageService.initCards(cardNum);
            modelAndView.addObject("number", cardNum);
        }else if(id == 3) {
            cardsList = manageService.deleteCard(cardId);
        }
        modelAndView.addObject("cardsList", cardsList);
        modelAndView.setViewName("initCard");
        return modelAndView;
    }

    /**
     * 卡片查询
     * @author Luke
     * @param id
     * @param cardNo
     * @return
     */
    @RequestMapping("/cardQuery/{id}")
    public ModelAndView cardQuery(@PathVariable("id") Integer id, String cardNo) {
        ModelAndView modelAndView = new ModelAndView();
        if(id == 2) {
            List<Cards> cardsList = manageService.showPersonData(cardNo);
            modelAndView.addObject("cardsList", cardsList);
            modelAndView.addObject("cardNo", cardNo);
        }
        modelAndView.setViewName("cardQuery");
        return modelAndView;
    }


    /**
     * 权限管理
     * @author Luke
     * @param id                 URL的id
     * @param prmeId             菜单-权限表id
     * @param prioNameStrId      权限表id
     * @param request
     * @return
     */
    @RequestMapping(value = "/prio/{id}")
    public ModelAndView prioManage(@PathVariable("id") Integer id,
                                   Integer prmeId,
                                   String prioNameStrId,
                                   HttpServletRequest request) {
        int prioNameId = 0;
        int menuNameId = 0;
        ModelAndView modelAndView = new ModelAndView();
        List<Priority> priorityList = null;
        List<Menus> menusList = null;
        List<Priority> prioMenuList = null;
        if(id == 2) {
            manageService.importPrio(path);
        }else if(id == 3) {
            String[] menuNamesId = request.getParameterValues("menuNameId");
            if(menuNamesId.length > 0) {
                for(String strId :menuNamesId) {
                    menuNameId = Integer.parseInt(strId);
                    prioNameId = Integer.parseInt(prioNameStrId);
                    manageService.relatePrioMenu(prioNameId, menuNameId);
                }
            }
        }else if(id == 4) {
            manageService.deleteMenuPrio(prmeId);
        }
        prioMenuList = manageService.queryPrioMenu("");
        priorityList = manageService.findPriority("");
        menusList = manageService.findMenus(0);
        modelAndView.addObject("prioMenuList", prioMenuList);
        modelAndView.addObject("menusList", menusList);
        modelAndView.addObject("priorityList", priorityList);
        modelAndView.setViewName("prioList");
        return modelAndView;
    }

    /**
     * 菜单管理
     * @param id
     * @param menuId
     * @return
     */
    @RequestMapping("/menu/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public ModelAndView menuManage(@PathVariable("id") Integer id,
                                   Integer menuId) {
//        List<Priority> priorityList = null;
        ModelAndView modelAndView = new ModelAndView();
        List<Menus> menusList = null;

        if(id == 2) {
            manageService.importMenu(path);
        }else if(id == 3) {
            manageService.deleteCard(menuId);
        }

        menusList = manageService.findMenus(0);
        modelAndView.addObject("menusList", menusList);
        modelAndView.setViewName("menuList");
        return modelAndView;
    }


    /**
     * 细项管理
     * @param id
     * @param subItemId 细项id
     * @return
     */
    @RequestMapping("/subItem/{id}")
    public ModelAndView subItemManage(@PathVariable("id") Integer id,
                                   Integer subItemId) {
        ModelAndView modelAndView = new ModelAndView();
        List<SubItem> subItemList = null;

        if(id == 2) {
            subItemList = manageService.importSubItem(path);
        }else if(id == 3) {
            subItemList = manageService.deleteSubItem(subItemId);
        }
        modelAndView.addObject("subItemList", subItemList);
        modelAndView.setViewName("subItemList");
        return modelAndView;
    }


    /**
     * 项目管理
     * @param id
     * @param
     * @return
     */
    @RequestMapping("/item/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public ModelAndView itemManage(@PathVariable("id") Integer id,
                                     Association association,
                                     Integer asitId,
                                     Integer assoIds,
                                     HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        List<Item> itemList = null;
        List<Office> officeList = null;
        List<ItemType> itemTypeList = null;
        List<Association> associationList = null;

        String message = "";
        if(id == 2) {
            message = manageService.importItem(path);
        }else if(id == 3) {
            int itemIds = Integer.parseInt(request.getParameter("itemId"));
            int offIds = Integer.parseInt(request.getParameter("offId"));
            int typeIds = Integer.parseInt(request.getParameter("typeId"));
            Item item = new Item();
            item.setItemId(itemIds);
            item.setOffId(offIds);
            item.setItemTypeId(typeIds);
            if(manageService.updateItem(item)) {
                message = "配置成功";
            }
        }else if(id == 4) {
            // 新增套餐
            String[] items = null;

            String assoName = association.getAssoName();
            double assoPrice = association.getAssoPrice();
            if(assoName != null && !("".equals(assoName.trim())) &&
                    manageService.findAssociation(assoName).size() < 1) {
                Association asso = new Association();
                asso.setAssoName(assoName);
                asso.setAssoPrice(assoPrice);
                items = request.getParameterValues("itemNameId");
                if(items != null && items.length > 0) {
                    manageService.insertAsso(asso);
                    int assoId = asso.getAssoId();
                    for(String item : items) {
                        int itemId = Integer.parseInt(item);
                        manageService.insertAssoItem(assoId, itemId);
                    }
                }
            }else{
                message = "套餐名不能为空，或已经存在此套餐";
            }
        }else if(id == 5) {
            // 删除套餐中的项目
            manageService.deleteAssoItem(asitId);


        }else if(id == 6) {
            // 删除套餐
            manageService.deleteAssoItemByAssoId(assoIds);
            manageService.deleteAsso(assoIds);



        }
        associationList = manageService.queryAssoItem("");
        itemList = manageService.findItem("");
        officeList = manageService.findOffice("");
        itemTypeList = manageService.findItemType("");
        modelAndView.addObject("associationList", associationList);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("officeList", officeList);
        modelAndView.addObject("itemTypeList", itemTypeList);
        modelAndView.addObject("message", message);
        modelAndView.setViewName("itemList");
        return modelAndView;
    }

    /**
     * 科室管理
     * @param id
     * @param offId
     * @return
     */
    @RequestMapping("/office/{id}")
    public ModelAndView officeManage(@PathVariable("id") Integer id,
                               Integer offId) {
        ModelAndView modelAndView = new ModelAndView();
        List<Office> offList = null;
        if(id == 2) {
            excelPlug.importOfficeExcelToDB(path);
        }
        offList = manageService.findOffice("");
        modelAndView.addObject("offList", offList);
        modelAndView.setViewName("officeList");
        return modelAndView;
    }

    @RequestMapping("/user/{id}")
    public ModelAndView userManage(@PathVariable("id") Integer id,
                               Users users,
                               Integer userId) {
        ModelAndView modelAndView = new ModelAndView();
        List<Users> userList = null;
        List<Office> offList = null;
        List<Priority> prioList = null;
        if(id == 2) {
            usersService.insertUsers(users);
        }if(id == 3) {
            usersService.deleteUsers(userId);
        }
        userList = manageService.queryUserOffPrio(0);
        offList = manageService.findOffice("");
        prioList = manageService.findPriority("");
        modelAndView.addObject("userList", userList);
        modelAndView.addObject("offList", offList);
        modelAndView.addObject("prioList", prioList);
        modelAndView.setViewName("userList");
        return modelAndView;
    }
}
