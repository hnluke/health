package com.controller;

import com.dao.UnionQueryMapper;
import com.google.gson.Gson;
import com.model.pojo.*;
import com.service.ICheckStationService;
//import com.sun.org.apache.regexp.internal.RE;
//import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.management.loading.MLet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/check")
public class CheckController {
    @Autowired
    private ICheckStationService checkStationService;
    @Resource
    private Apps apps;
    @Resource
    private Batches batches;
    @Resource
    private Cards cards;
    @Resource
    private Person person;

    /**
     * 开单业务
     * @param cardNo 体检人卡编号
     */
    @RequestMapping("/list/{id}")
    public ModelAndView createList(@PathVariable("id") Integer id, String cardNo, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        List<String> namesList = null;
        namesList = new ArrayList<String>();
        if(id == 1) {
            request.getSession().setAttribute("namesList", namesList);
        }else if(id == 2) {

            // namesList-用于保存用户选择的项目或套餐的名称session的集合
            namesList = (List<String>)request.getSession().getAttribute("namesList");
            // 如果用户没有选择，则返回并提示用户没有选择项目
            if(namesList == null || namesList.size() < 1) {
                modelAndView.addObject("message", "您没有选择任何体检项目");
            }else{
                String message = "";
                //
                message = checkStationService.createList(cardNo, namesList);
                modelAndView.addObject("message", message);
            }
        }

        List<Association>  assoList = checkStationService.showAllAsso("");
        List<Item> itemList = checkStationService.showAllItem("");
        modelAndView.addObject("assoList", assoList);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("cardNo", cardNo);
        modelAndView.setViewName("createList");
        namesList.clear();
        request.getSession().setAttribute("namesList", namesList);

        return modelAndView;

    }

    /**
     * 导检单
     * @param id    根据不同的id处理不同业务; 1:显示导检页面; 2: 显示导检列表; 3显示要打印的导检单页面
     * @param batchId   流水表id
     * @param cardNo    卡片编号
     * @return
     */
    @RequestMapping("/guide/{id}")
    public ModelAndView showGuide(@PathVariable("id") Integer id,
                                  Integer batchId,
                                  String cardNo) {

        ModelAndView modelAndView = new ModelAndView();

        if(id == 2) {
            // 选择了"打印导检单和条码"
            //Batches batches = new Batches();
            //Cards cards = new Cards();
            batches.setBatchPay("未缴费");
            batches.setBatchCmp("未完成");
            cards.setCardNo(cardNo);
            List<Batches> guideList = checkStationService.showGuideGheck(batches, person, cards);
            modelAndView.addObject("guideList", guideList);
            modelAndView.addObject("cardNo", cardNo);
        }else if(id == 3) {
            // 选择打印
            List<Briefs> guideData = checkStationService.showGuideData(batchId);
            modelAndView.addObject("guideData", guideData);
            modelAndView.setViewName("guide");
            return modelAndView;
        }
        modelAndView.setViewName("guideList");
        return modelAndView;
    }





    @ResponseBody
    @RequestMapping(value="/jsondata", produces = {"application/json;charset=utf-8"})
    public List<Selects> recieveJson(@RequestBody String jsonStr, HttpServletRequest request) {
        List<String> namesList = null;
        if(request.getSession().getAttribute("namesList") == null) {
            namesList = new ArrayList<String>();
        }else {
            namesList = (ArrayList<String>)request.getSession().getAttribute("namesList");
        }
        Gson gson = new Gson();
        apps = gson.fromJson(jsonStr, Apps.class);

        List<Selects> listSelect = null;
        System.out.println(apps);
        if("1".equals(apps.getChk())) {
            if(apps.getAssoName() != null) {
                namesList.add(apps.getAssoName());
            }else{
                namesList.add(apps.getItemName());

            }

            listSelect = checkStationService.insertSelect(apps.getAssoName(), apps.getItemName());
        }else if("0".equals(apps.getChk())) {
            if(apps.getAssoName() != null) {
                namesList.remove(apps.getAssoName());
            }else{
                namesList.remove(apps.getItemName());
            }
            listSelect = checkStationService.deleteSelect(apps.getAssoId(), apps.getItemId());
        }
        request.getSession().setAttribute("namesList", namesList);
        return listSelect;
    }

    @RequestMapping("/report/{id}")
    public ModelAndView showReport(@PathVariable("id") Integer id,
                                  String batchNo,
                                  String cardNo) {

        ModelAndView modelAndView = new ModelAndView();

        if(id == 2) {
            // 选择了"显示符合条件的体检报告列表"
            Batches batches = new Batches();
            Cards cards = new Cards();
            batches.setBatchPay("未缴费");
            batches.setBatchCmp("未完成");
            cards.setCardNo(cardNo);
            List<Batches> reportList = checkStationService.showGuideGheck(batches, new Person(), cards);
            modelAndView.addObject("reportList", reportList);
            modelAndView.addObject("cardNo", cardNo);
        }else if(id == 3) {
            // 选择打印体检报告
            List<Briefs> reportData = checkStationService.showReportData(batchNo);
            modelAndView.addObject("reportData", reportData);
            modelAndView.setViewName("report");
            return modelAndView;
        } else if (id == 4) {
            // 选择打印总结报告
            List<Batches> sumData = checkStationService.showSumData(batchNo);
            modelAndView.addObject("sumData", sumData);
            modelAndView.setViewName("chksum");
            return modelAndView;

        }

        modelAndView.setViewName("reportList");
        return modelAndView;
    }

    /**
     * 综合体检查询
     * @param id
     * @param batchNo
     * @param cardNo
     * @return
     */
    @RequestMapping("/compre/{id}")
    public ModelAndView showCompre(@PathVariable("id") Integer id,
                                   String batchNo,
                                   String cardNo) {
        ModelAndView modelAndView = new ModelAndView();
        if(id == 2){
            cards.setCardNo(cardNo);
            List<Batches> compresList = checkStationService.showGuideGheck(batches, person, cards);
            modelAndView.addObject("compresList", compresList);
            modelAndView.addObject("cardNo", cardNo);
        }else if(id == 3) {
            List<Briefs> reportDate = checkStationService.showReportData(batchNo);
            modelAndView.addObject("reportDate", reportDate);
            modelAndView.setViewName("report");
            return modelAndView;
        }else if(id == 4){
            List<Batches> sumData = checkStationService.showSumData(batchNo);
            modelAndView.addObject("sumData", sumData);
            modelAndView.setViewName("chksum");
            return modelAndView;

        }
        modelAndView.setViewName("compreList");
        return modelAndView;

    }


    @RequestMapping("/persons/{id}")
    public ModelAndView showPerson(@PathVariable("id") Integer id,
                                   String cardNo) {
        ModelAndView modelAndView = new ModelAndView();
        if(id == 2){
            List<Cards> perList = checkStationService.showPersonData(cardNo);
            modelAndView.addObject("perList", perList);
        }else if(id == 3){
            List<Cards> perData = checkStationService.showPersonData(cardNo);
            modelAndView.addObject("perData", perData);
            modelAndView.setViewName("persons");
            return modelAndView;
        }else if(id == 4) {

        }
        modelAndView.setViewName("personList");
        return modelAndView;

    }
}
