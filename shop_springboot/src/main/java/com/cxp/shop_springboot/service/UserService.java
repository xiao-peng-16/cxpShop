package com.cxp.shop_springboot.service;

import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.pojo.messageResponse.Message_User_FlagEnter;

import javax.servlet.http.HttpSession;

public interface UserService {

    //注册
    public Boolean insUser(User user);
    public Boolean is_usable_username(String username);

    //登录
    public User selUserByPassword(User user);

    // 根据id
    public User selUserById(int id);

    public Message_User_FlagEnter selUser_FlagEnterById(HttpSession session);

}
