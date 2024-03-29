package com.cxp.shop_springboot.pojo;

public class User {

    int uID;
    String username;
    String password;
    String photoname;
    double money;

    int shop_CarCount; //购物车数量

    public int getShop_CarCount() {
        return shop_CarCount;
    }

    public void setShop_CarCount(int shop_CarCount) {
        this.shop_CarCount = shop_CarCount;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhotoname(String photoname) {
        this.photoname = photoname;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "uID=" + uID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", photoname='" + photoname + '\'' +
                ", money=" + money +
                '}';
    }

    public int getuID() {
        return uID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhotoname() {
        return photoname;
    }

    public double getMoney() {
        return money;
    }


}
