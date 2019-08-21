package com.cxp.shop_springboot.mapper;

import com.cxp.shop_springboot.pojo.Order;

import com.cxp.shop_springboot.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Shop_CarMapper {

    //购物车页面数据
    public List<Order> selShop_Car_PageDataByUID(@Param("uID") int uID);

    //查询用户所有订单
    @Select("select * from shop_car where uID=#{uID} ")
    public List<Order> selShop_CarByUID(@Param("uID") int uID);

    //查询用户购物车数量
    @Select("select sum(shop_number) from shop_car where uID=#{uID} ")
    public Integer selShop_CarCountByUID(@Param("uID") int uID);

    //增加一条订单
    @Insert("insert shop_car(uID,cID,shop_number) values(#{uID},#{cID},#{shop_number})")
    public int insShop_Car(Order order);

    //删除一条订单 根据这条订单id
    @Delete("delete from shop_car where shop_carID=#{shop_carID}")
    public int delShop_CarByID(Order order);

    //更改该条订单的购买数量 根据订单id
    @Update("update shop_car set shop_number=#{shop_number} where shop_carID=#{shop_carID}")
    public int addCommodityStock_CarByshop_carID(Order order);

    //更改该条订单的购买数量 根据 用户id 和 商品id
    @Update("update shop_car set shop_number=shop_number+#{shop_number} where uID=#{uID} and cID=#{cID}")
    public int addCommodityStock_CarBy_UID_CID(Order order);
}
