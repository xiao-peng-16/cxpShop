package com.cxp.shop_springboot.service;

import com.cxp.shop_springboot.pojo.Commodity;
import com.cxp.shop_springboot.pojo.Order;

import com.cxp.shop_springboot.pojo.request.SearchPage_Request;
import com.cxp.shop_springboot.pojo.User;
import com.cxp.shop_springboot.pojo.response.ResponseBean;

import java.util.List;
import java.util.Map;

public interface CommodityService {


    //返回搜索页的搜索结果
    public Map<String,Object> selSearchPageData(SearchPage_Request searchPage_request);
    //返回搜索页的商品列表
    public List<Commodity> selSearchList(SearchPage_Request searchPage_request);
    //返回搜索结果共多少个
    public Integer selCountSearchList(SearchPage_Request searchPage_request);
    //根据商品id返回 一个商品数据
    public Commodity selSearchDataByCID(SearchPage_Request searchPage_request);
    //交易后更改商品表 商品数量
    public Boolean addCommodityStock(User user, List<Order> orderList) throws Exception;
    //用户购买那些商品几件
    public ResponseBean UserShop(List<Order> orderList, User user);

}
