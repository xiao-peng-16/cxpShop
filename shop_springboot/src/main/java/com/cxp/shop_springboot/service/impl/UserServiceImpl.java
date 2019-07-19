package com.cxp.shop_springboot.service.impl;

import com.cxp.shop_springboot.mapper.UserMapper;
import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.pojo.messageResponse.Message_User_FlagEnter;
import com.cxp.shop_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

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
    public User selUserById(int id) {
        return userMapper.selUserById(id);
    }

    @Override
    public Message_User_FlagEnter selUser_FlagEnterById(HttpSession session) {

        Integer uid = (Integer) session.getAttribute("uid");
        Message_User_FlagEnter message = new Message_User_FlagEnter();
        //默认值
        message.setFlag_enter(false);
        if (uid!=null){
            User user = userMapper.selUserById(uid);
            if (user!=null){
                user.setPassword("");
                message.setFlag_enter(true);
                message.setUser(user);
            }else {
                message.setMsg("用户id错误");
            }
        }else {
            message.setMsg("用户未登录,session的id为空值");
        }
        return message;
    }


}
