package com.cxp.shop_springboot.service.impl;

import com.cxp.shop_springboot.mapper.Shop_CarMapper;
import com.cxp.shop_springboot.pojo.Order;
import com.cxp.shop_springboot.pojo.response.*;
import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.service.CommodityService;
import com.cxp.shop_springboot.service.Shop_CarService;
import com.cxp.shop_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Shop_CarServiceImpl implements Shop_CarService {

    @Autowired
    UserService userService;
    @Autowired
    CommodityServiceImpl commodityService;
    @Autowired
    Shop_CarMapper shop_carMapper;

    @Override
    public Boolean addShop_Car(Order order) {
        int updLen = shop_carMapper.addCommodityStock_CarBy_UID_CID(order);
        if (updLen>0){
            return true;
        }else {
            int insLen = shop_carMapper.insShop_Car(order);
            return insLen>0;
        }
    }

    @Override
    public ResponseBean selShop_Car_PageDataByUID(int uid) {
        List<Order> orderList = shop_carMapper.selShop_Car_PageDataByUID(uid);
        return ResponseFactory.createSuccessResponse(orderList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseBean Shop_Car_Shop(List<Order> orderList, User user){
        ResponseBean responseBean = commodityService.UserShop(orderList, user);
        //  删除购物车表信息
        if (responseBean.isSuccess()){
            for (Order order : orderList){
                shop_carMapper.delShop_CarByID(order);
            }
        }
        return responseBean;
    }

    @Override
    public ResponseBean delShop_Car(List<Order> orderList) {
        for (Order order : orderList){
            shop_carMapper.delShop_CarByID(order);
        }
        return ResponseFactory.createSuccessResponse();
    }


}
