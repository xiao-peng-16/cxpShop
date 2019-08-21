package com.cxp.shop_springboot.service.impl;

import com.cxp.shop_springboot.mapper.CommodityMapper;
import com.cxp.shop_springboot.mapper.OrderFormMapper;
import com.cxp.shop_springboot.mapper.StoreMapper;
import com.cxp.shop_springboot.pojo.Commodity;
import com.cxp.shop_springboot.pojo.Order;
import com.cxp.shop_springboot.pojo.Store;
import com.cxp.shop_springboot.pojo.response.ResponseBean;
import com.cxp.shop_springboot.pojo.response.ResponseFactory;
import com.cxp.shop_springboot.pojo.response.ResponseStatus;
import com.cxp.shop_springboot.pojo.response.pojo.FullStoreSales;
import com.cxp.shop_springboot.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    StoreMapper storeMapper;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    OrderFormMapper orderFormMapper;

    @Override
    public ResponseBean selStoreById(int userId) {
        Store store = storeMapper.selStoreById(userId);
        if (null!=store){
            return ResponseFactory.createSuccessResponse(store);
        }
        return ResponseFactory.createFailResponse(ResponseStatus.STORE_NOT_QUALIFICATION);
    }

    @Override
    public ResponseBean selStoreCommodityList(int userId) {
            List<Commodity> commodityList = commodityMapper.selStoreCommodityList(userId);
            return ResponseFactory.createSuccessResponse(commodityList);
    }

    @Override
    public ResponseBean selStoreOrderList(int storeId) {
        List<Order> orderList = orderFormMapper.selStoreOrderList(storeId);
        return ResponseFactory.createSuccessResponse(orderList);
    }


    @Override
    public ResponseBean selFullStoreSales(int storeId) {
        FullStoreSales fullStoreSales = new FullStoreSales();
        fullStoreSales.setCurrentMonth(orderFormMapper.selStoreSalesCurrentMonth(storeId));
        fullStoreSales.setTotality(orderFormMapper.selStoreSalesEarnings(storeId));
        return ResponseFactory.createSuccessResponse(fullStoreSales);
    }




}
