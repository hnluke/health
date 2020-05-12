package com.controller;

import com.dao.CardsMapper;
import com.dao.UnionQueryMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.pojo.*;
import com.service.IChargeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.security.jgss.GSSCaller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.servlet.function.EntityResponse.fromObject;

@Controller
@RequestMapping("/charge")
public class ChargeController {
    @Resource
    private IChargeService chargeService;
    @Resource
    private UnionQueryMapper unionQueryMapper;
    @Resource
    private Batches batches;
    @Resource
    private Person person;
    @Resource
    private Cards cards;

    @RequestMapping("/createCards/{id}")
    public ModelAndView createCards(@PathVariable("id") Integer id,
                                    Person person) {
        ModelAndView modelAndView = new ModelAndView();
        List<Cards> cardsList = null;
        if(person.getPerName() == null || "".equals(person.getPerName().trim()))
        System.out.println(person);
        if(id == 2) {
            chargeService.createCards(person);
        }
        cardsList = chargeService.queryCardsPerson("");
        System.out.println("=====================================");
        System.out.println(cardsList);
        System.out.println("=====================================");
        modelAndView.addObject("cardsList", cardsList);
        modelAndView.setViewName("createCardsList");
        return modelAndView;
    }

    /**
     * 获取未用的卡号
     * @param jsonStr
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/jsondata", produces = {"application/json;charset=utf-8"})
    public List<Cards> fetchCards(@RequestBody String jsonStr, HttpServletRequest request) {
        List<Cards> cardsList = chargeService.fetchCardNo();
        return cardsList;
    }

    @RequestMapping("/balance/{id}")
    public ModelAndView balan(@PathVariable("id") Integer id,
                              Integer listId, Integer batchId, String cardNo) {
        ModelAndView modelAndView = new ModelAndView();
        List<Lists> listsList = null;
        String message = "";
        if(id == 2) {
            //体检缴费
            List<Cards> cardsList = chargeService.findCard(cardNo);
            List<Lists> lists = chargeService.findLists(listId);
            if(cardsList.get(0).getCardMoney() < lists.get(0).getListPrice()) {
                message = "余额不足";
            }else{
                chargeService.updateBriefsPay(listId, "已缴费");
                chargeService.updateBatchesPay(batchId, "已缴费");
            }
        }else if(id == 3) {
            // 体检退费

        }
        listsList = unionQueryMapper.queryPayBalance(batches, cards);
        modelAndView.addObject("listsList", listsList);
        modelAndView.addObject("message", message);
        modelAndView.setViewName("balanceList");
        return modelAndView;

    }

    @ResponseBody
    @RequestMapping(value="/balaJson", produces = {"application/json;charset=utf-8"})
    public List<Lists> pay(@RequestBody String jsonStr, HttpServletRequest request) {
        Gson gson = new Gson();
        Apps apps = gson.fromJson(jsonStr, Apps.class);
        List<Lists> listsList = null;
        if("体检缴费".equals(apps.getParam())) {
            // 显示待缴费的项目
            batches.setBatchPay("未缴费");
            batches.setBatchCmp("未完成");
            cards.setCardNo(apps.getCardNo());


        }else if("体检退费".equals(apps.getParam())){
            // 显示可以退费的项目
            batches.setBatchPay("已缴费");
            batches.setBatchCmp("未完成");
            cards.setCardNo(apps.getCardNo());
        }
        listsList = unionQueryMapper.queryPayBalance(batches, cards);
        return listsList;


    }

}
