package com.cxp.shop_springboot.pojo;

public class Order_Commodity {
    int shop_carID; //该订单id
    int uID;        //用户id
    int cID;        //商品id
    int shop_number;     //购买数量

    @Override
    public String toString() {
        return "Order_Commodity{" +
                "shop_carID=" + shop_carID +
                ", uID=" + uID +
                ", cID=" + cID +
                ", shop_number=" + shop_number +
                '}';
    }

    public int getShop_carID() {
        return shop_carID;
    }

    public void setShop_carID(int shop_carID) {
        this.shop_carID = shop_carID;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public int getShop_number() {
        return shop_number;
    }

    public void setShop_number(int shop_number) {
        this.shop_number = shop_number;
    }
}
