package com.cxp.shop_springboot.mapper;

import com.cxp.shop_springboot.pojo.Commodity;
import com.cxp.shop_springboot.pojo.Store;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreMapper {

    @Select("select * from store where storeID = #{storeID}")
    public Store selStoreById(@Param("storeID") int storeID);


}
