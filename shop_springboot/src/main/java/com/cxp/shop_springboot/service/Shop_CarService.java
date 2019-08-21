package com.cxp.shop_springboot.service;

import com.cxp.shop_springboot.pojo.Order;

import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.pojo.response.ResponseBean;

import java.util.List;

public interface Shop_CarService {

    //用户点击 加入购物车
    public Boolean addShop_Car(Order order);

    //购物车页面数据
    public ResponseBean selShop_Car_PageDataByUID(int uid);

    //购物车 购物
    public ResponseBean Shop_Car_Shop(List<Order> orderList, User user);

    //删除一条订单根据订单id
    public ResponseBean delShop_Car(List<Order> orderList);
}
