package com.cxp.shop_springboot.pojo;

public class Store {
    int storeID;
    String storeName;
    String sProvince; //省
    String sCity;     //市
    float sGradeDescribe;
    float sGradeAttitude;
    float sGradeDeliverySpeed;

    @Override
    public String toString() {
        return "Store{" +
                "storeID=" + storeID +
                ", storeName='" + storeName + '\'' +
                ", sProvince='" + sProvince + '\'' +
                ", sCity='" + sCity + '\'' +
                ", sGradeDescribe=" + sGradeDescribe +
                ", sGradeAttitude=" + sGradeAttitude +
                ", sGradeDeliverySpeed=" + sGradeDeliverySpeed +
                '}';
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getsProvince() {
        return sProvince;
    }

    public void setsProvince(String sProvince) {
        this.sProvince = sProvince;
    }

    public String getsCity() {
        return sCity;
    }

    public void setsCity(String sCity) {
        this.sCity = sCity;
    }

    public float getsGradeDescribe() {
        return sGradeDescribe;
    }

    public void setsGradeDescribe(float sGradeDescribe) {
        this.sGradeDescribe = sGradeDescribe;
    }

    public float getsGradeAttitude() {
        return sGradeAttitude;
    }

    public void setsGradeAttitude(float sGradeAttitude) {
        this.sGradeAttitude = sGradeAttitude;
    }

    public float getsGradeDeliverySpeed() {
        return sGradeDeliverySpeed;
    }

    public void setsGradeDeliverySpeed(float sGradeDeliverySpeed) {
        this.sGradeDeliverySpeed = sGradeDeliverySpeed;
    }
}
