package com.cxp.shop_springboot.pojo.messageResponse;

import com.cxp.shop_springboot.pojo.Commodity;

public class Message_Shop_Car_Commodity {
    int shop_carID;     //订单id
    Commodity commodity;    //商品
    int shop_number;     //购买数量

    @Override
    public String toString() {
        return "Message_Shop_Car_Commodity{" +
                "shop_carID=" + shop_carID +
                ", commodity=" + commodity +
                ", shop_number=" + shop_number +
                '}';
    }

    public int getShop_carID() {
        return shop_carID;
    }

    public void setShop_carID(int shop_carID) {
        this.shop_carID = shop_carID;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public int getShop_number() {
        return shop_number;
    }

    public void setShop_number(int shop_number) {
        this.shop_number = shop_number;
    }
}
