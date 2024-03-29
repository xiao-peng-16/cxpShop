package com.cxp.shop_springboot.controller;

import com.cxp.shop_springboot.pojo.Order;
import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.pojo.response.ResponseBean;
import com.cxp.shop_springboot.pojo.response.ResponseFactory;
import com.cxp.shop_springboot.pojo.response.ResponseStatus;
import com.cxp.shop_springboot.service.impl.Shop_CarServiceImpl;
import com.cxp.shop_springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/*

    购物车
 */
@CrossOrigin(allowCredentials = "true")
@RestController
public class Shop_CarController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    Shop_CarServiceImpl shop_carService;


    @RequestMapping("/addShop_Car")
    public ResponseBean addShop_Car(Order order, HttpSession session){
        Integer userId = userService.selUserId(session);
        if (userId != null){
            order.setuID(userId);
            shop_carService.addShop_Car(order);
            return ResponseFactory.createSuccessResponse();
        }else {
            return ResponseFactory.createFailResponse(ResponseStatus.USER_LOGIN_OVERDUE);
        }
    }


    @RequestMapping("/selShop_Car_PageDataByUID")
    public ResponseBean selShop_Car_PageDataByUID(HttpSession session) {
        Integer userId = userService.selUserId(session);
        if (userId != null){
            return shop_carService.selShop_Car_PageDataByUID(userId);
        }else {
            return ResponseFactory.createFailResponse(ResponseStatus.USER_LOGIN_OVERDUE);
        }
    }

    @RequestMapping("/Shop_Car_Shop")
    public ResponseBean Shop_Car_Shop(@RequestBody List<Order> orderList, HttpSession session){

        User user = userService.selUserById(session);
        if (user != null){
            return shop_carService.Shop_Car_Shop(orderList,  user);
        }else {
            return ResponseFactory.createFailResponse(ResponseStatus.USER_LOGIN_OVERDUE);
        }

    }


    @RequestMapping("/delShop_Car")
    public ResponseBean  delShop_CarByID(@RequestBody List<Order> orderList, HttpSession session){

        Integer userId = userService.selUserId(session);
        if (userId != null){
            return shop_carService.delShop_Car( orderList);
        }else {
            return ResponseFactory.createFailResponse(ResponseStatus.USER_LOGIN_OVERDUE);
        }
    }
}
