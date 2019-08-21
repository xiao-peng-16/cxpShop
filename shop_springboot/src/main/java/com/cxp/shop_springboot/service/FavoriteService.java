package com.cxp.shop_springboot.service;

import com.cxp.shop_springboot.pojo.Favorite;
import com.cxp.shop_springboot.pojo.Order;
import com.cxp.shop_springboot.pojo.response.ResponseBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface FavoriteService {

    public ResponseBean addFavorite(Favorite favorite);

    public ResponseBean selFavoriteList(int uID);

    public ResponseBean delFavorite(List<Favorite> favoriteList);


    public ResponseBean shop_car_To_Favorite(List<Order> orderList,int userId);

}
