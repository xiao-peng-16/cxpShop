package com.cxp.shop_springboot.controller;

import com.cxp.shop_springboot.pojo.Order_Commodity;
import com.cxp.shop_springboot.pojo.messageResponse.Message_Shop;
import com.cxp.shop_springboot.pojo.messageResponse.Message_Shop_Car_Commodity;
import com.cxp.shop_springboot.pojo.messageResponse.Message_User_FlagEnter;
import com.cxp.shop_springboot.service.impl.Shop_CarServiceImpl;
import com.cxp.shop_springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(allowCredentials = "true")
@RestController
public class Shop_CarController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    Shop_CarServiceImpl shop_carService;


    @RequestMapping("/addShop_Car")
    public Map<String,Object> addShop_Car(Order_Commodity order_commodity, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        Message_User_FlagEnter message_user_flagEnter = userService.selUser_FlagEnterById(session);

        if (message_user_flagEnter.isFlag_enter()){
            map.put("flag_enter",true);
            order_commodity.setuID(message_user_flagEnter.getUser().getuID());
            boolean flag = shop_carService.addShop_Car(order_commodity);
            map.put("flag_addShop_Car",flag);
        }else {
            map.put("flag_enter",false);
            map.put("msg",message_user_flagEnter.getMsg());
        }
        return map;
    }


    @RequestMapping("/selShop_Car_PageDataByUID")
    public Map<String,Object> selShop_Car_PageDataByUID(HttpSession session) {
        Map<String,Object> map = new HashMap<>();
        Message_User_FlagEnter message_user_flagEnter = userService.selUser_FlagEnterById(session);
        if (message_user_flagEnter.isFlag_enter()){
            map.put("flag_enter",true);
            List<Message_Shop_Car_Commodity> listData = shop_carService.selShop_Car_PageDataByUID(message_user_flagEnter.getUser());
            if (listData!=null){
                map.put("flag_selShop_Car",true);
                map.put("listData",listData);
            }
        }else {
            map.put("flag_selShop_Car",false);
            map.put("flag_enter",false);
            map.put("msg",message_user_flagEnter.getMsg());
        }
        return map;
    }

    @RequestMapping("/Shop_Car_Shop")
    public Message_Shop Shop_Car_Shop(@RequestBody List<Order_Commodity> order_commodityList, HttpSession session){
        return shop_carService.Shop_Car_Shop(order_commodityList,  session);
    }


    @RequestMapping("/delShop_CarByID")
    public Boolean  delShop_CarByID(Order_Commodity order_commodity, HttpSession session){
        return shop_carService.delShop_CarByID( order_commodity,  session);
    }
}
