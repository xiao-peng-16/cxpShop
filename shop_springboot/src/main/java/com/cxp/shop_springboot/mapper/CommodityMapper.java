package com.cxp.shop_springboot.mapper;

import com.cxp.shop_springboot.pojo.Commodity;
import com.cxp.shop_springboot.pojo.Order;
import com.cxp.shop_springboot.pojo.request.SearchPage_Request;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CommodityMapper {
    //返回搜索页的搜索结果
    public List<Commodity> selSearchList(SearchPage_Request searchPage_request);
    //返回搜索结果共多少个
    public Integer selCountSearchList(SearchPage_Request searchPage_request);
    //根据商品id返回 一个商品完整数据
    public Commodity selSearchDataByCID(SearchPage_Request searchPage_request);
    //根据商品id返回 一个商品简单数据
    @Select("select cID,storeID,cName,cPrice,cStock from commodity where cID = #{cID}")
    public Commodity selSimplifyDataByCID(@Param("cID") Integer cID);
    //浏览商品 人气+1
    @Update("update commodity set cPopularity=cPopularity+1 where cID =#{cID}")
    public int updAddCPopularityByID(SearchPage_Request searchPage_request);
    //交易后更改商品表 商品数量
    @Update("update commodity set cStock=cStock - #{shop_number},cSalesVolume=cSalesVolume + #{shop_number} where cID =#{cID}")
    public int addCommodityStock(Order order);
    //查找 商家出售哪些商品
    @Select("select cID,cName,cPhotoname,cPrice,cSalesVolume,cStock from commodity where storeID = #{storeID}")
    public List<Commodity> selStoreCommodityList(@Param("storeID") int storeID);
}
