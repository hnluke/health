package com.controller;


import com.model.pojo.Users;
import com.service.IUsersService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UsersController {
    //是否记住密码
    boolean isMe;
    private static Logger log= LoggerFactory.getLogger(UsersController.class);
    @Resource
    private IUsersService usersService;
    @ResponseBody
    @RequestMapping(value = "/login",method= RequestMethod.POST)
    public ModelAndView login(Users user, HttpSession session,
                              HttpServletResponse response, HttpServletRequest request,  String isMemory){
        if(isMemory==null){
            isMe=false;
        }else{
            isMe=true;
        }
        ModelAndView modelAndView = new ModelAndView();
        boolean flag = false;
        flag = usersService.verify(
                user.getUserName(), user.getUserPwd());
        if(flag) {
            try {
                isMemory(isMe, user, request, response);
            } catch (UnsupportedEncodingException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
            session.setAttribute("user", user.getUserName());
            modelAndView.setViewName("main");

        }else {
            session.setAttribute("user", "");
            modelAndView.setViewName("redirect:/login.jsp");
        }

        return modelAndView;
    }

    /*
	 是否记住密码
	 **/
    private void isMemory(boolean isMe,Users user,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        if (isMe) {
            Cookie userName = new Cookie("userName", user.getUserName());
            userName.setPath("/");
            Cookie userPwd = new Cookie("userPwd", user.getUserPwd());
            userPwd.setPath("/");
            response.addCookie(userName);
            response.addCookie(userPwd);
        } else {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.equals("userName") | cookie.equals("userPwd")) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
    }


    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView();
        List<Users> userList  = usersService.findAll("");
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("changePwd");
        return modelAndView;
    }


    //关于修改密码的表单提交
    @RequestMapping("/changePwd")
    public ModelAndView changePwd(@RequestParam("newPwd") String newPwd, @RequestParam("userPwd") String userPwd, @RequestParam("userId") Integer userId, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();

        int rows = usersService.changePwd(userPwd, newPwd,userId);
        if(rows > 0){
            if(newPwd.equals(userPwd)){
                modelAndView.addObject("msg","修改密码失败！新密码不能与旧密码相同！");
            }else {
                modelAndView.addObject("msg", "修改密码成功！");
            }

        }else{
            modelAndView.addObject("msg","修改密码失败！");
        }


        List<Users> userList  = usersService.findAll("");
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("changePwd");
        return modelAndView;
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        session.invalidate();    // 获取session信息，使session信息失效，直接返回登录界面，并连接跳转。
        modelAndView.setViewName("redirect:/login.jsp");
        return modelAndView;
    }



    @RequestMapping("")
    public String showLogin() {
        return "login";
    }


}
