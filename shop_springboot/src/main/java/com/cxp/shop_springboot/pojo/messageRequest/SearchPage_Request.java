package com.cxp.shop_springboot.pojo.messageRequest;

public class SearchPage_Request {
    public static final int PRICE_ASC = 1;          //价格升序
    public static final int PRICE_DESC = 2;         //价格降序
    public static final int SALES_VOLUME_DESC = 3;  //销量降序
    public static final int POPULARITY_DESC = 4;    //人气降序

    public static final int searchSort_cName = 1;   //按商品名字搜索
    public static final int searchSort_sortname = 2;//按商品种类搜索
    public static final int searchSort_storeName = 3;//按店铺搜索

    String searchWord;  //搜索词
    Integer searchSort;  //按 名字 ， 商品种类 ，店铺 搜索
    Integer cID;        //商品id
    Integer sortID;     //商品种类id
    Integer storeID;       //店铺id

    Integer pageNo;//  分页 页码
    Integer pageStepSize;//  分页 步长
    Integer pageStartLen;//  分页 第几行开始  计算得来

    @Override
    public String toString() {
        return "SearchPage_Request{" +
                "searchWord='" + searchWord + '\'' +
                ", searchSort=" + searchSort +
                ", cID=" + cID +
                ", sortID=" + sortID +
                ", storeID=" + storeID +
                ", pageNo=" + pageNo +
                ", pageStepSize=" + pageStepSize +
                ", pageStartLen=" + pageStartLen +
                '}';
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageStepSize() {
        return pageStepSize;
    }

    public void setPageStepSize(Integer pageStepSize) {
        this.pageStepSize = pageStepSize;
    }

    public Integer getPageStartLen() {
        return pageStartLen;
    }

    public void setPageStartLen(Integer pageStartLen) {
        this.pageStartLen = pageStartLen;
    }

    public static int getPriceAsc() {
        return PRICE_ASC;
    }

    public static int getPriceDesc() {
        return PRICE_DESC;
    }

    public static int getSalesVolumeDesc() {
        return SALES_VOLUME_DESC;
    }

    public static int getPopularityDesc() {
        return POPULARITY_DESC;
    }

    public static int getSearchSort_cName() {
        return searchSort_cName;
    }

    public static int getSearchSort_sortname() {
        return searchSort_sortname;
    }

    public static int getSearchSort_storeName() {
        return searchSort_storeName;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public Integer getSearchSort() {
        return searchSort;
    }

    public void setSearchSort(Integer searchSort) {
        this.searchSort = searchSort;
    }

    public Integer getcID() {
        return cID;
    }

    public void setcID(Integer cID) {
        this.cID = cID;
    }

    public Integer getSortID() {
        return sortID;
    }

    public void setSortID(Integer sortID) {
        this.sortID = sortID;
    }

    public Integer getStoreID() {
        return storeID;
    }

    public void setStoreID(Integer storeID) {
        this.storeID = storeID;
    }
}
