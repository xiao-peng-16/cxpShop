package com.cxp.shop_springboot.service.impl;

import com.cxp.shop_springboot.mapper.Shop_CarMapper;
import com.cxp.shop_springboot.pojo.Order_Commodity;
import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.pojo.messageResponse.Message_Shop;
import com.cxp.shop_springboot.pojo.messageResponse.Message_Shop_Car_Commodity;
import com.cxp.shop_springboot.pojo.messageResponse.Message_User_FlagEnter;
import com.cxp.shop_springboot.service.CommodityService;
import com.cxp.shop_springboot.service.Shop_CarService;
import com.cxp.shop_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class Shop_CarServiceImpl implements Shop_CarService {

    @Autowired
    UserService userService;
    @Autowired
    CommodityService commodityService;
    @Autowired
    Shop_CarMapper shop_carMapper;

    @Override
    public Boolean addShop_Car(Order_Commodity order_commodity) {
        int updLen = shop_carMapper.updShop_CarBy_UID_CID(order_commodity);
        if (updLen>0){
            return true;
        }else {
            int insLen = shop_carMapper.insShop_Car(order_commodity);
            return insLen>0;
        }
    }

    @Override
    public List<Message_Shop_Car_Commodity> selShop_Car_PageDataByUID(User user) {
        return shop_carMapper.selShop_Car_PageDataByUID(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Message_Shop Shop_Car_Shop(List<Order_Commodity> order_commodityList, HttpSession session){
        Message_Shop message_shop = commodityService.UserShop(order_commodityList, session);
        //  删除购物车表信息
        if (message_shop.isFlag_shop()){
            for (Order_Commodity order_commodity : order_commodityList){
                shop_carMapper.delShop_CarByID(order_commodity);
            }
        }

        return message_shop;
    }

    @Override
    public Boolean delShop_CarByID(Order_Commodity order_commodity, HttpSession session) {
        Message_User_FlagEnter message_user_flagEnter = userService.selUser_FlagEnterById(session);

        return shop_carMapper.delShop_CarByID(order_commodity)>0;
    }

}
