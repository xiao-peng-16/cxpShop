package com.cxp.shop_springboot.pojo;


/*

    购物车 、收藏夹 、 订单、卖家中心商品列表  共用
 */
public class Order {
    int shop_carID; //买家加入购物车 的编号
    int fID;  //收藏夹 编号
    int oID;        //买家购买 交易形成的订单 的编号

    int uID;        //用户id
    int storeID;    //卖家id
    int cID;        //商品id
    int shop_number;//'数量',

    String cName;       //商品名
    String cPhotoname;  //商品图片
    String username;

    double cPrice;     //'商品价格',
    double oSumPrice;    //'该订单总金额',

    String oDateTime; //'时间'


    @Override
    public String toString() {
        return "Order{" +
                "shop_carID=" + shop_carID +
                ", fID=" + fID +
                ", oID=" + oID +
                ", uID=" + uID +
                ", storeID=" + storeID +
                ", cID=" + cID +
                ", shop_number=" + shop_number +
                ", cName='" + cName + '\'' +
                ", cPhotoname='" + cPhotoname + '\'' +
                ", username='" + username + '\'' +
                ", cPrice=" + cPrice +
                ", oSumPrice=" + oSumPrice +
                ", oDateTime='" + oDateTime + '\'' +
                '}';
    }

    public int getShop_carID() {
        return shop_carID;
    }

    public void setShop_carID(int shop_carID) {
        this.shop_carID = shop_carID;
    }

    public int getfID() {
        return fID;
    }

    public void setfID(int fID) {
        this.fID = fID;
    }

    public int getoID() {
        return oID;
    }

    public void setoID(int oID) {
        this.oID = oID;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public int getShop_number() {
        return shop_number;
    }

    public void setShop_number(int shop_number) {
        this.shop_number = shop_number;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcPhotoname() {
        return cPhotoname;
    }

    public void setcPhotoname(String cPhotoname) {
        this.cPhotoname = cPhotoname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getcPrice() {
        return cPrice;
    }

    public void setcPrice(double cPrice) {
        this.cPrice = cPrice;
    }

    public double getoSumPrice() {
        return oSumPrice;
    }

    public void setoSumPrice(double oSumPrice) {
        this.oSumPrice = oSumPrice;
    }

    public String getoDateTime() {
        return oDateTime;
    }

    public void setoDateTime(String oDateTime) {
        this.oDateTime = oDateTime;
    }
}
