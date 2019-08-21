package com.cxp.shop_springboot.service.impl;

import com.cxp.shop_springboot.mapper.Shop_CarMapper;
import com.cxp.shop_springboot.mapper.UserMapper;
import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    Shop_CarMapper shop_carMapper;
    @Override
    public Boolean insUser(User user) {
        return userMapper.insUser(user)>0;
    }

    @Override
    public Boolean is_usable_username(String username) {
        return userMapper.is_usable_username(username)==0;
    }

    @Override
    public User selUserByPassword(User user) {
        List<User> userList = userMapper.selUserByPassword(user);
        if (userList.size()!=0){
            return userMapper.selUserByPassword(user).get(0);
        }else {
            return null;
        }

    }

    @Override
    public User selUserById(int userId) {
        User user = userMapper.selUserById(userId);
        Integer shop_CarCount = shop_carMapper.selShop_CarCountByUID(userId);
        if (null!=shop_CarCount){
            user.setShop_CarCount(shop_CarCount);
        }else {
            user.setShop_CarCount(0);
        }
        return user;
    }

    @Override
    public User selUserById(HttpSession session) {
        Integer uid = (Integer) session.getAttribute("uid");
        //默认值
        if (uid!=null){
            return selUserById(uid);
        }else {
            return null;
        }
    }

    @Override
    public Integer selUserId(HttpSession session) {
        return (Integer) session.getAttribute("uid");
    }


}
