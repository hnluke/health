package com.controller;

import com.model.pojo.Association;
import com.model.pojo.Item;
import com.service.ICheckStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/check")
public class CheckController {
    @Autowired
    private ICheckStationService checkStationService;
    @RequestMapping("/list")
    public ModelAndView createList() {
        ModelAndView modelAndView = new ModelAndView();
        List<Association>  assoList = checkStationService.showAllAsso("");
        List<Item> itemList = checkStationService.showAllItem("");
        modelAndView.addObject("assoList", assoList);
        modelAndView.addObject("itemList", itemList);
        modelAndView.setViewName("createList");
        return modelAndView;

    }

    @RequestMapping("/list2")
    public ModelAndView createList2() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        return modelAndView;

    }
}
