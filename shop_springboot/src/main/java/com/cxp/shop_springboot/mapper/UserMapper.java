package com.cxp.shop_springboot.mapper;

import com.cxp.shop_springboot.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    //注册
    @Insert("insert user(username,password) values(#{username},#{password})")
    public int insUser(User user);

    @Select("select count(*) from user where username = #{username}")
    public int  is_usable_username(@Param("username") String username);


    //登录
    @Select("select * from user where username = #{username} and password = #{password}")
    public List<User> selUserByPassword(User user);


    //id登录
    @Select("select * from user where uID = #{uID}")
    public User selUserById(@Param("uID") int uID);

    //购物 买家减少余额
    @Update("update user set money=money-#{sunPrice} where uID = #{uID}")
    public int subUserMoney(@Param("uID") int uID, @Param("sunPrice") double sunPrice);

    //购物 卖家增加余额
    @Update("update user set money=money+#{sunPrice} where uID = #{uID}")
    public int addUserMoney(@Param("uID") int uID, @Param("sunPrice") double sunPrice);
}
