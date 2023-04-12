package cn.itcast.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ShopBean implements Serializable {
    private static final long serialVersionUID = 1L; //序列化时保持ShopBean类版本的兼容性
    private int id;                            	//店铺Id
    private String shopName;                 	//店铺名称
    private int saleNum;                      	//月售数量
    private BigDecimal offerPrice;          	//起送价格
    private BigDecimal distributionCost;  	//配送费用
    private String feature;                  	//店铺特色
    private String time;                      	//配送时间
    private String banner;                    	//广告栏图片
    private String shopPic;                  	//店铺图片
    private String shopNotice;              	//店铺公告
    private List<FoodBean> foodList;       	//菜单列表
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getShopName() {
        return shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public int getSaleNum() {
        return saleNum;
    }
    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }
    public BigDecimal getOfferPrice() {
        return offerPrice;
    }
    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }
    public BigDecimal getDistributionCost() {
        return distributionCost;
    }
    public void setDistributionCost(BigDecimal distributionCost) {
        this.distributionCost = distributionCost;
    }
    public String getFeature() {
        return feature;
    }
    public void setFeature(String feature) {
        this.feature = feature;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getShopPic() {
        return shopPic;
    }
    public String getBanner() {
        return banner;
    }
    public void setBanner(String banner) {
        this.banner = banner;
    }
    public void setShopPic(String shopPic) {
        this.shopPic = shopPic;
    }
    public String getShopNotice() {
        return shopNotice;
    }
    public void setShopNotice(String shopNotice) {
        this.shopNotice = shopNotice;
    }
    public List<FoodBean> getFoodList() {
        return foodList;
    }
    public void setFoodList(List<FoodBean> foodList) {
        this.foodList = foodList;
    }
}

