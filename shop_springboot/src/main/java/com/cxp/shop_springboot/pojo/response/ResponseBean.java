package com.cxp.shop_springboot.pojo.response;

public class ResponseBean {
    private boolean success;
    private int status;

    private Object data;



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getstatus() {
        return status;
    }

    public void setstatus(int status) {
        this.status = status;
    }

    public ResponseBean(boolean success, int status) {
        this.success = success;
        this.status = status;
    }

    public ResponseBean(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "success=" + success +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
