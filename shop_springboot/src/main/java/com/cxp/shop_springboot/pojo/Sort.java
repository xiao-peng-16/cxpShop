package com.cxp.shop_springboot.pojo;

public class Sort {

    int sortID;
    String sortname;

    @Override
    public String toString() {
        return "Sort{" +
                "sortID=" + sortID +
                ", sortname='" + sortname + '\'' +
                '}';
    }

    public int getSortID() {
        return sortID;
    }

    public void setSortID(int sortID) {
        this.sortID = sortID;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }
}
