package com.cxp.shop_springboot.pojo.messageResponse;

import com.cxp.shop_springboot.pojo.User;

public class Message_User_FlagEnter {
    boolean flag_enter;
    User user;
    String msg;

    @Override
    public String toString() {
        return "Message_User_FlagEnter{" +
                "flag_enter=" + flag_enter +
                ", user=" + user +
                ", msg='" + msg + '\'' +
                '}';
    }

    public boolean isFlag_enter() {
        return flag_enter;
    }

    public void setFlag_enter(boolean flag_enter) {
        this.flag_enter = flag_enter;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
