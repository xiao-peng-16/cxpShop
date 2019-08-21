

export default {
  flag_enter:false,

  baseURL:"http://localhost:8080",
  commodityImagesUrl:"http://localhost/resource/commodity/images/",
  commodityVideoUrl:"http://localhost/resource/commodity/video/",


  // baseURL:"http://47.107.36.244:8080/shop_springboot",
  // commodityImagesUrl:"http://47.107.36.244/commodity/images/",
  // commodityVideoUrl:"http://47.107.36.244/commodity/video/"
  //

  user:{},

  soller_center_background:'#F8F8F8',



  ResponseStatus:{
//登录 2xx
    USER_LOGIN_ERROR : 201,         //账号密码不对
    USER_LOGIN_ID_ERROR : 202,         //账号ID不存在
    USER_LOGIN_OVERDUE : 203,       //用户过期或未登录

    //买家 3xx
    SHOP_NOT_SUFFICIENT_FUNDS : 301,

    //商品 4xx
    COMMODITY_ID_ERROR : 401,         //账号ID不存在
    COMMODITY_STOCK_INSUFFICIENT : 402,         //账号ID不存在
    //商家 5xx
  },

}
  
