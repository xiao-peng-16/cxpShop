package com.cxp.shop_springboot.controller;

import com.cxp.shop_springboot.pojo.messageResponse.Message_User_FlagEnter;
import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@CrossOrigin(allowCredentials = "true")
@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    //判断用户名是否可以注册 (防止已经有人注册过了)
    @RequestMapping("/is_usable_username")
    public Boolean is_usable_username(String username) {
        return userService.is_usable_username(username);
    }
    //创建用户
    @RequestMapping("/insUser")
    public Boolean insUser(User user) {
        return userService.insUser(user);
    }
    //账号 密码登录
    @RequestMapping("/selUserByPassword")
    public Boolean selUserByPassword(User usersrc, HttpSession session) {
        Message_User_FlagEnter message = new Message_User_FlagEnter();
        User userResulf = userService.selUserByPassword(usersrc);
        if (userResulf!=null){
            session.setAttribute("uid",userResulf.getuID());
            return true;
        }else {
            return false;
        }
    }
    //根据session 的id 返回用户
    @RequestMapping("/selUserById")
    public Message_User_FlagEnter selUserById(HttpSession session) {
        return userService.selUser_FlagEnterById(session);
    }
    //退出登录
    @RequestMapping("/outEnter")
    public Boolean outEnter(HttpSession session){
        session.invalidate();
        return true;
    }


}