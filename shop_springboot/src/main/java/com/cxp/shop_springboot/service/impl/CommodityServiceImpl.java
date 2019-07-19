package com.cxp.shop_springboot.service.impl;

import com.cxp.shop_springboot.mapper.CommodityMapper;
import com.cxp.shop_springboot.mapper.UserMapper;
import com.cxp.shop_springboot.pojo.Commodity;
import com.cxp.shop_springboot.pojo.Order_Commodity;
import com.cxp.shop_springboot.pojo.messageRequest.SearchPage_Request;
import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.pojo.messageResponse.Message_Shop;
import com.cxp.shop_springboot.pojo.messageResponse.Message_User_FlagEnter;
import com.cxp.shop_springboot.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    CommodityMapper commodityMapper;

    public void setPageStartLen(SearchPage_Request searchPage_request) {
        if(searchPage_request.getPageNo()!=null && searchPage_request.getPageStepSize()!=null){
            searchPage_request.setPageStartLen((searchPage_request.getPageNo()-1) * searchPage_request.getPageStepSize());
        }
    }

    @Override
    public Integer selCountSearchList(SearchPage_Request searchPage_request) {
        return commodityMapper.selCountSearchList(searchPage_request);
    }
    @Override
    public List<Commodity> selSearchList(SearchPage_Request searchPage_request) {
        return  commodityMapper.selSearchList(searchPage_request);
    }

    public boolean success_Search(SearchPage_Request searchPage_request, Map<String,Object> map){
        int commoditySum = selCountSearchList(searchPage_request);
        if (commoditySum>0){
            map.put("commoditySum",commoditySum);
            map.put("commodityList",selSearchList(searchPage_request));
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Map<String,Object> selSearchPageData(SearchPage_Request searchPage_request) {
        setPageStartLen(searchPage_request);
        Map<String,Object> map = new HashMap<>();
        map.put("commoditySum",0);      //初始化
        map.put("commodityList",null);

        searchPage_request.setSearchSort(SearchPage_Request.searchSort_storeName);
        if (success_Search(searchPage_request,map)){
            return map;
        }
        searchPage_request.setSearchSort(SearchPage_Request.searchSort_sortname);
        if (success_Search(searchPage_request,map)){
            return map;
        }
        searchPage_request.setSearchSort(SearchPage_Request.searchSort_cName);
        if (success_Search(searchPage_request,map)){
            return map;
        }
        return map;
    }




    @Override
    public Commodity selSearchDataByCID(SearchPage_Request searchPage_request) {
        commodityMapper.updAddCPopularityByID(searchPage_request);
        return commodityMapper.selSearchDataByCID(searchPage_request);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updShop(User user, List<Order_Commodity> order_commodityList) throws Exception {
        List<Commodity> commodityList = new ArrayList<>();
        double sunPrice=0;
        for ( Order_Commodity order_commodity : order_commodityList){
            Commodity commodity = commodityMapper.selSimplifyDataByCID(order_commodity.getcID());
            if (commodity==null) { throw new Exception(order_commodity.getcID()+"的selSimplifyDataByCID结果为null"); }
            if (commodity.getcStock()<=0){ throw new Exception(commodity.getcName()+"commodity库存不大于0"); }
            sunPrice += commodity.getcPrice()*order_commodity.getShop_number();
            commodityList.add(commodity);
        }
        if (user.getMoney() < sunPrice) {
            throw new Exception("user余额不足");
        }else {
            //买家扣钱
            int userLen = userMapper.updUserMoney(user.getuID(), sunPrice);
            if (userLen==0){throw new RuntimeException("userLen==0");}
            //卖家 商品数量变动
            for ( Order_Commodity order_commodity : order_commodityList){
                int commodityLen = commodityMapper.updShop(order_commodity);
                if (commodityLen==0){throw new RuntimeException("commodityLen==0");}
            }
            return true;
        }

    }



    public Message_Shop UserShop(List<Order_Commodity> order_commodityList, HttpSession session){
        Message_Shop message_shop = new Message_Shop();

        Message_User_FlagEnter message_user_flagEnter = userService.selUser_FlagEnterById(session);

        if (message_user_flagEnter.isFlag_enter()){
            message_shop.setFlag_enter(true);
            try {
                boolean bool = updShop(message_user_flagEnter.getUser(), order_commodityList);
                message_shop.setFlag_shop(bool);
            } catch (Exception e) {
                e.printStackTrace();
                message_shop.setFlag_shop(false);
                message_shop.setMsg(e.toString());
            }
        }else {
            message_shop.setFlag_shop(false);
            message_shop.setFlag_enter(false);
            message_shop.setMsg(message_user_flagEnter.getMsg());
        }
        return message_shop;
    }

}
