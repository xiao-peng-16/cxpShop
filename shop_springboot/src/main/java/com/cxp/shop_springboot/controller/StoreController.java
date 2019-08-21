package com.cxp.shop_springboot.controller;

import com.cxp.shop_springboot.pojo.response.ResponseBean;
import com.cxp.shop_springboot.pojo.response.ResponseFactory;
import com.cxp.shop_springboot.pojo.response.ResponseStatus;
import com.cxp.shop_springboot.service.impl.StoreServiceImpl;
import com.cxp.shop_springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@CrossOrigin(allowCredentials = "true")

@RestController
public class StoreController {

    @Autowired
    StoreServiceImpl storeService;
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/selStoreById")
    public ResponseBean selStoreById(HttpSession session){
        Integer userId = userService.selUserId(session);
        if (userId != null){
            return storeService.selStoreById(userId);
        }else {
            return ResponseFactory.createFailResponse(ResponseStatus.USER_LOGIN_OVERDUE);
        }
    }

    @RequestMapping("/selStoreCommodityList")
    public ResponseBean selStoreCommodityList(HttpSession session){
        Integer userId = userService.selUserId(session);
        if (userId != null){
            return storeService.selStoreCommodityList(userId);
        }else {
            return ResponseFactory.createFailResponse(ResponseStatus.USER_LOGIN_OVERDUE);
        }
    }

    @RequestMapping("/selStoreOrderFormList")
    public ResponseBean selStoreOrderFormList(HttpSession session){
        Integer userId = userService.selUserId(session);
        if (userId != null){
            return storeService.selStoreOrderList(userId);
        }else {
            return ResponseFactory.createFailResponse(ResponseStatus.USER_LOGIN_OVERDUE);
        }
    }

    @RequestMapping("/selFullStoreSales")
    public ResponseBean selFullStoreSales(HttpSession session){
        Integer userId = userService.selUserId(session);
        if (userId != null){
            return storeService.selFullStoreSales(userId);
        }else {
            return ResponseFactory.createFailResponse(ResponseStatus.USER_LOGIN_OVERDUE);
        }
    }
}
