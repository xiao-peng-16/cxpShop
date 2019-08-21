package com.cxp.shop_springboot.controller;

import com.cxp.shop_springboot.pojo.Order;
import com.cxp.shop_springboot.pojo.response.ResponseBean;
import com.cxp.shop_springboot.pojo.response.ResponseFactory;
import com.cxp.shop_springboot.pojo.response.ResponseStatus;
import com.cxp.shop_springboot.service.impl.FavoriteServiceImpl;
import com.cxp.shop_springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin(allowCredentials = "true")
@RestController
public class FavoriteController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    FavoriteServiceImpl favoriteService;


    @RequestMapping("/shop_car_To_Favorite")
    public ResponseBean  shop_car_To_Favorite(@RequestBody List<Order> orderList, HttpSession session){
        Integer userId = userService.selUserId(session);
        if (userId != null){
            return favoriteService.shop_car_To_Favorite(orderList,userId);
        }else {
            return ResponseFactory.createFailResponse(ResponseStatus.USER_LOGIN_OVERDUE);
        }
    }


    @RequestMapping("/selFavoriteList")
    public ResponseBean selFavoriteList(HttpSession session){
        Integer userId = userService.selUserId(session);
        if (userId != null){
            return favoriteService.selFavoriteList(userId);
        }else {
            return ResponseFactory.createFailResponse(ResponseStatus.USER_LOGIN_OVERDUE);
        }
    }


}
