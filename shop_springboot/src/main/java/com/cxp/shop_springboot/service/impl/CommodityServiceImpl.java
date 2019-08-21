package com.cxp.shop_springboot.service.impl;

import com.cxp.shop_springboot.mapper.CommodityMapper;
import com.cxp.shop_springboot.mapper.OrderFormMapper;
import com.cxp.shop_springboot.mapper.UserMapper;
import com.cxp.shop_springboot.pojo.Commodity;
import com.cxp.shop_springboot.pojo.Order;

import com.cxp.shop_springboot.pojo.request.SearchPage_Request;
import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.pojo.response.ResponseBean;
import com.cxp.shop_springboot.pojo.response.ResponseFactory;
import com.cxp.shop_springboot.pojo.response.ResponseStatus;
import com.cxp.shop_springboot.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
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
    @Autowired
    OrderFormMapper orderFormMapper;

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
    public Boolean addCommodityStock(User user, List<Order> orderList) throws CommodityByIdException, CommodityStockInsufficientException, UserNotSufficientFundsException {
        double sunPrice=0;
        for ( Order order : orderList){
            Commodity commodity = commodityMapper.selSimplifyDataByCID(order.getcID());
            if (commodity==null) { throw new CommodityByIdException(order.getcID()+"的selSimplifyDataByCID结果为null"); }
            if (commodity.getcStock()< order.getShop_number()){
                throw new CommodityStockInsufficientException(commodity.getcName()+"commodity库存不足");
            }
            order.setuID(user.getuID());
            order.setStoreID(commodity.getStoreID());
            order.setcPrice(commodity.getcPrice());
            order.setoSumPrice(commodity.getcPrice()*order.getShop_number());
            sunPrice += order.getoSumPrice();

        }
        if (user.getMoney() < sunPrice) {
            throw new UserNotSufficientFundsException("user余额不足");
        }else {
            //买家扣钱
            userMapper.subUserMoney(user.getuID(), sunPrice);
            //卖家 商品数量变动  加钱
            for ( Order order : orderList){
                //商品库存 销量更新
                commodityMapper.addCommodityStock(order);
                //商家  赚钱
                userMapper.addUserMoney(order.getStoreID(),order.getoSumPrice());
                //生成订单
                orderFormMapper.addOrderForm(order);
            }
            return true;
        }

    }


    @Override
    public ResponseBean UserShop(List<Order> orderList, User user){
            try {
                addCommodityStock(user, orderList);
                return ResponseFactory.createSuccessResponse();
            } catch (CommodityStockInsufficientException e) {
                e.printStackTrace();
                return ResponseFactory.createFailResponse(ResponseStatus.COMMODITY_STOCK_INSUFFICIENT);
            } catch (CommodityByIdException e) {
                e.printStackTrace();
                return ResponseFactory.createFailResponse(ResponseStatus.COMMODITY_ID_ERROR);
            } catch (UserNotSufficientFundsException e) {
                e.printStackTrace();
                return ResponseFactory.createFailResponse(ResponseStatus.SHOP_NOT_SUFFICIENT_FUNDS);
            }
    }


    static class UserNotSufficientFundsException extends Exception{
        public UserNotSufficientFundsException(String message) {
            super(message);
        }
    }
    static class CommodityByIdException extends Exception{
        public CommodityByIdException(String message) {
            super(message);
        }
    }
    static class CommodityStockInsufficientException extends Exception{
        public CommodityStockInsufficientException(String message) {
            super(message);
        }
    }
}
