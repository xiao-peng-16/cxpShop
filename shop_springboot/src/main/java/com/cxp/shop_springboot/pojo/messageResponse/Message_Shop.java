package com.cxp.shop_springboot.pojo.messageResponse;

public class Message_Shop {
    boolean flag_shop;
    boolean flag_enter;
    String msg;


    @Override
    public String toString() {
        return "Message_Shop{" +
                "flag_shop=" + flag_shop +
                ", flag_enter=" + flag_enter +
                ", msg='" + msg + '\'' +
                '}';
    }

    public boolean isFlag_enter() {
        return flag_enter;
    }

    public void setFlag_enter(boolean flag_enter) {
        this.flag_enter = flag_enter;
    }

    public boolean isFlag_shop() {
        return flag_shop;
    }

    public void setFlag_shop(boolean flag_shop) {
        this.flag_shop = flag_shop;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
