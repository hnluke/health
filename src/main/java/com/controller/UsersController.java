package com.controller;


import com.model.pojo.Users;
import com.service.IUsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UsersController {
    @Resource
    private IUsersService usersService;
    @RequestMapping(value = "/login")
    public ModelAndView login(Users users, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
//        boolean flag = false;
//        flag = usersService.verify(
//                users.getUserName(), users.getUserPwd());
//        if(flag) {
//            session.setAttribute("user", users.getUserName());
//            modelAndView.setViewName("main");
//        }else {
//            session.setAttribute("user", "");
//            modelAndView.setViewName("redirect:/login.jsp");
//        }
        modelAndView.setViewName("main");

        return modelAndView;
    }



    @RequestMapping("")
    public String showLogin() {
        return "login";
    }


}
