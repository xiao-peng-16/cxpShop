package com.cxp.shop_springboot.service.impl;

import com.cxp.shop_springboot.mapper.FavoriteMapper;
import com.cxp.shop_springboot.mapper.Shop_CarMapper;
import com.cxp.shop_springboot.pojo.Favorite;
import com.cxp.shop_springboot.pojo.Order;
import com.cxp.shop_springboot.pojo.response.ResponseBean;
import com.cxp.shop_springboot.pojo.response.ResponseFactory;
import com.cxp.shop_springboot.pojo.response.ResponseStatus;
import com.cxp.shop_springboot.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteMapper favoriteMapper;
    @Autowired
    Shop_CarMapper shop_carMapper;

    @Override
    public ResponseBean addFavorite(Favorite favorite) {
        if (0<favoriteMapper.addFavoriteFavorite(favorite)){
            return ResponseFactory.createSuccessResponse();
        }
        return ResponseFactory.createFailResponse(ResponseStatus.COLLECT_CREATE_DEFEATED);
    }

    @Override
    public ResponseBean selFavoriteList(int uID) {
        return ResponseFactory.createSuccessResponse(favoriteMapper.selFavoriteList(uID));
    }

    @Override
    public ResponseBean delFavorite(List<Favorite> favoriteList){
        for (Favorite favorite :favoriteList){
            favoriteMapper.delFavorite(favorite);
        }
        return ResponseFactory.createSuccessResponse();
    }

    @Override
    public ResponseBean shop_car_To_Favorite(List<Order> orderList,int userId) {
        for (Order order : orderList){
            order.setuID(userId);
            if (0 == favoriteMapper.selFavoriteCountByOrder(order)){
                favoriteMapper.addFavoriteByOrder(order);
            }
            shop_carMapper.delShop_CarByID(order);
        }
        return ResponseFactory.createSuccessResponse();
    }
}
