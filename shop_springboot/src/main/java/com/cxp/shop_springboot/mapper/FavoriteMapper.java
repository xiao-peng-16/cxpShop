package com.cxp.shop_springboot.mapper;

import com.cxp.shop_springboot.pojo.Favorite;
import com.cxp.shop_springboot.pojo.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteMapper {

    @Insert("insert favorite(uID,cID) values(#{uID},#{cID})")
    public int addFavoriteFavorite(Favorite favorite);

    @Insert("insert favorite(uID,cID) values(#{uID},#{cID})")
    public int addFavoriteByOrder(Order order);

    //判断 是否重复
    @Select("select count(*) from favorite where uID=#{uID} and cID = #{cID}")
    public int selFavoriteCountByOrder(Order order);

    public List<Order> selFavoriteList(@Param("uID") int uID);

    @Delete("delete from favorite where uID=#{uID} and cID = #{cID}")
    public int delFavorite(Favorite favorite);

}
