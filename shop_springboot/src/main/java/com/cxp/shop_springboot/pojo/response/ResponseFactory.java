package com.cxp.shop_springboot.pojo.response;

public class ResponseFactory {
    public static ResponseBean createSuccessResponse(){
        return new ResponseBean(true);
    }
    public static ResponseBean createSuccessResponse(Object data){
        ResponseBean responseBean = new ResponseBean(true);
        responseBean.setData(data);
        return responseBean;
    }

    public static ResponseBean createFailResponse(int status){
        return new ResponseBean(false,status);
    }
    public static ResponseBean createFailResponse(int status,Object data){
        ResponseBean responseBean = new ResponseBean(false,status);
        responseBean.setData(data);
        return responseBean;
    }
}
