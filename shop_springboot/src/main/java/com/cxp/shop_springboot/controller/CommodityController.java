package com.cxp.shop_springboot.controller;

import com.cxp.shop_springboot.pojo.Commodity;
import com.cxp.shop_springboot.pojo.Order_Commodity;
import com.cxp.shop_springboot.pojo.messageRequest.SearchPage_Request;
import com.cxp.shop_springboot.pojo.messageResponse.Message_Shop;
import com.cxp.shop_springboot.service.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(allowCredentials = "true")
@RestController
public class CommodityController {

    @Autowired
    CommodityServiceImpl commodityService;

    @RequestMapping("/selSearchPageData")
    public Map<String,Object> selSearchPageData(SearchPage_Request searchPage_request) {
        return commodityService.selSearchPageData(searchPage_request);
    }

    @RequestMapping("/selSearchDataByCID")
    public Commodity selSearchDataByCID(SearchPage_Request searchPage_request){
        return  commodityService.selSearchDataByCID(searchPage_request);
    }

    @RequestMapping("/UserShop")
    public Message_Shop UserShop(@RequestBody List<Order_Commodity> order_commodityList, HttpSession session){
        return commodityService.UserShop(order_commodityList,  session);
    }


}
