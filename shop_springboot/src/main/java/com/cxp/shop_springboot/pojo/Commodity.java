package com.cxp.shop_springboot.pojo;

public class Commodity {
    String cName;       //商品名
    int cID;
    Store store;        //商品所属店
    Sort sort; //商品所属种类
    String cDescribe;   //商品描述
    Boolean bao_you;     //是否包邮
    String cPhotoname;  //商品图片
    String cVideoname;   //商品视频'
    double cPrice;      //价格
    int cSalesVolume;   //销量
    int cPopularity;    //人气 (点击量)
    int cStock;         //库存

    @Override
    public String toString() {
        return "Commodity{" +
                "cName='" + cName + '\'' +
                ", cID=" + cID +
                ", store=" + store +
                ", sort=" + sort +
                ", cDescribe='" + cDescribe + '\'' +
                ", bao_you=" + bao_you +
                ", cPhotoname='" + cPhotoname + '\'' +
                ", cVideoname='" + cVideoname + '\'' +
                ", cPrice=" + cPrice +
                ", cSalesVolume=" + cSalesVolume +
                ", cPopularity=" + cPopularity +
                ", cStock=" + cStock +
                '}';
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public String getcDescribe() {
        return cDescribe;
    }

    public void setcDescribe(String cDescribe) {
        this.cDescribe = cDescribe;
    }

    public Boolean getBao_you() {
        return bao_you;
    }

    public void setBao_you(Boolean bao_you) {
        this.bao_you = bao_you;
    }

    public String getcPhotoname() {
        return cPhotoname;
    }

    public void setcPhotoname(String cPhotoname) {
        this.cPhotoname = cPhotoname;
    }

    public String getcVideoname() {
        return cVideoname;
    }

    public void setcVideoname(String cVideoname) {
        this.cVideoname = cVideoname;
    }

    public double getcPrice() {
        return cPrice;
    }

    public void setcPrice(double cPrice) {
        this.cPrice = cPrice;
    }

    public int getcSalesVolume() {
        return cSalesVolume;
    }

    public void setcSalesVolume(int cSalesVolume) {
        this.cSalesVolume = cSalesVolume;
    }

    public int getcPopularity() {
        return cPopularity;
    }

    public void setcPopularity(int cPopularity) {
        this.cPopularity = cPopularity;
    }

    public int getcStock() {
        return cStock;
    }

    public void setcStock(int cStock) {
        this.cStock = cStock;
    }
}
