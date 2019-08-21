package com.cxp.shop_springboot.controller;

import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.pojo.response.ResponseBean;
import com.cxp.shop_springboot.pojo.response.ResponseFactory;
import com.cxp.shop_springboot.pojo.response.ResponseStatus;
import com.cxp.shop_springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


/*
        用户
 */

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
    public ResponseBean selUserByPassword(User usersrc, HttpSession session) {
        User userResulf = userService.selUserByPassword(usersrc);
        if (userResulf!=null){
            session.setAttribute("uid",userResulf.getuID());
            return ResponseFactory.createSuccessResponse(userResulf);
        }else {
            return ResponseFactory.createFailResponse(ResponseStatus.USER_LOGIN_ERROR);
        }
    }
    //根据session 的id 返回用户
    @RequestMapping("/selUserById")
    public ResponseBean selUserById(HttpSession session) {
        User user = userService.selUserById(session);
        if (user != null){
            return ResponseFactory.createSuccessResponse(user);
        }else {
            return ResponseFactory.createFailResponse(ResponseStatus.USER_LOGIN_OVERDUE);
        }
    }
    //退出登录
    @RequestMapping("/outEnter")
    public Boolean outEnter(HttpSession session){
        session.invalidate();
        return true;
    }


}