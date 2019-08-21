package com.cxp.shop_springboot.mapper;


import com.cxp.shop_springboot.pojo.Order;
import com.cxp.shop_springboot.pojo.response.pojo.StoreSalesBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFormMapper {

    //添加订单
    @Insert("insert order_form(uID,storeID,cID,shop_number,cPrice,oSumPrice) values(#{uID},#{storeID},#{cID},#{shop_number},#{cPrice},#{oSumPrice})")
    public int addOrderForm(Order orderForm);
    //查询改卖家所有订单
    public List<Order> selStoreOrderList(@Param("storeID") int storeID);

    //卖家单月 出售情况
    @Select("select sum(shop_number) as SalesVolume, sum(oSumPrice) as Earnings from order_form  where storeID = #{storeID} and DATE_FORMAT(oDateTime,'%Y%m')=DATE_FORMAT(CURDATE(),'%Y%m')")
    public StoreSalesBean selStoreSalesCurrentMonth(@Param("storeID") int storeID);
    //卖家  总 出售情况
    @Select("select sum(shop_number) as SalesVolume, sum(oSumPrice) as Earnings from order_form  where storeID = #{storeID}")
    public StoreSalesBean selStoreSalesEarnings(@Param("storeID") int storeID);
}
