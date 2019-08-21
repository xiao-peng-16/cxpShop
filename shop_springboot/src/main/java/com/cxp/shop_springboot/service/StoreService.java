package com.cxp.shop_springboot.service;

import com.cxp.shop_springboot.pojo.response.ResponseBean;

public interface StoreService {

    //查询 店铺信息 根据id
    public ResponseBean selStoreById(int userId);
    //查询 店铺商品列表
    public ResponseBean selStoreCommodityList(int userId);
    //查询 店铺 整体销售情况
    public ResponseBean selFullStoreSales(int storeId);

    public ResponseBean selStoreOrderList(int storeId) ;
}
