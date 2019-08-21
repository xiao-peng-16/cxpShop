package com.cxp.shop_springboot.controller;

import com.cxp.shop_springboot.pojo.Commodity;
import com.cxp.shop_springboot.pojo.Order;

import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.pojo.request.SearchPage_Request;
import com.cxp.shop_springboot.pojo.response.ResponseBean;
import com.cxp.shop_springboot.pojo.response.ResponseFactory;
import com.cxp.shop_springboot.pojo.response.ResponseStatus;
import com.cxp.shop_springboot.service.impl.CommodityServiceImpl;
import com.cxp.shop_springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/*

    商品
 */
@CrossOrigin(allowCredentials = "true")
@RestController
public class CommodityController {

    @Autowired
    CommodityServiceImpl commodityService;
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/selSearchPageData")
    public Map<String,Object> selSearchPageData(SearchPage_Request searchPage_request) {
        return commodityService.selSearchPageData(searchPage_request);
    }

    @RequestMapping("/selSearchDataByCID")
    public Commodity selSearchDataByCID(SearchPage_Request searchPage_request){
        return  commodityService.selSearchDataByCID(searchPage_request);
    }

    @RequestMapping("/UserShop")
    public ResponseBean UserShop(@RequestBody List<Order> orderList, HttpSession session){
        User user = userService.selUserById(session);
        if (user != null){
            return commodityService.UserShop(orderList, user);
        }else {
            return ResponseFactory.createFailResponse(ResponseStatus.USER_LOGIN_OVERDUE);
        }
    }


}
