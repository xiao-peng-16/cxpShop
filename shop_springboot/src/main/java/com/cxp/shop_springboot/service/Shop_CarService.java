package com.cxp.shop_springboot.service;

import com.cxp.shop_springboot.pojo.Order_Commodity;
import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.pojo.messageResponse.Message_Shop;
import com.cxp.shop_springboot.pojo.messageResponse.Message_Shop_Car_Commodity;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface Shop_CarService {

    //用户点击 加入购物车
    public Boolean addShop_Car(Order_Commodity order_commodity);

    //购物车页面数据
    public List<Message_Shop_Car_Commodity> selShop_Car_PageDataByUID(User user);

    //购物车 购物
    public Message_Shop Shop_Car_Shop(List<Order_Commodity> order_commodityList, HttpSession session);

    //删除一条订单根据订单id
    public Boolean delShop_CarByID(Order_Commodity order_commodity, HttpSession session);
}
