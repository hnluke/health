package com.controller;

import com.model.pojo.Cards;
import com.model.pojo.MenuPrio;
import com.model.pojo.Menus;
import com.model.pojo.Priority;
import com.service.IManageService;
import com.service.impl.ManageServiceImpl;
import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/menu/{id}")
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public ModelAndView menuManage(@PathVariable("id") Integer id,
                                   Integer menuId,
                                   Menus menus) {
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

    


}
