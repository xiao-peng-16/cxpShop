package com.cxp.shop_springboot.service;

import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.pojo.response.ResponseBean;

import javax.servlet.http.HttpSession;

public interface UserService {

    //注册
    public Boolean insUser(User user);
    public Boolean is_usable_username(String username);

    //登录
    public User selUserByPassword(User user);

    // 根据id
    public User selUserById(int  userId);
    public User selUserById(HttpSession session);


    public Integer selUserId(HttpSession session);
}
