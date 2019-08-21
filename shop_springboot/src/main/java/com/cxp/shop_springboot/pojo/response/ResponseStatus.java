package com.cxp.shop_springboot.pojo.response;

public class ResponseStatus {

    //登录 2xx
    public final static int USER_LOGIN_ERROR = 201;         //账号密码不对
    public final static int USER_LOGIN_ID_ERROR = 202;         //账号ID不存在
    public final static int USER_LOGIN_OVERDUE = 203;       //用户过期或未登录

    //买家 3xx
    public final static int SHOP_NOT_SUFFICIENT_FUNDS = 301;    //买家与额余额不足

    //商品 4xx
    public final static int COMMODITY_ID_ERROR = 401;         //商品ID不存在
    public final static int COMMODITY_STOCK_INSUFFICIENT = 402;         //商品库存不足买家购买
    //商家 5xx
    public final static int STORE_NOT_QUALIFICATION = 501;  //未开通 卖家功能
    //数据库添加失败  6xx
    public final static int USER_CREATE_DEFEATED = 601;       //用户注册失败
    public final static int SHOPCAR_CREATE_DEFEATED = 601;    //购物车添加失败
    public final static int COLLECT_CREATE_DEFEATED = 601;    //收藏夹添加失败
    public final static int ORDER_FORM_CREATE_DEFEATED = 601; //订单添加失败

}
