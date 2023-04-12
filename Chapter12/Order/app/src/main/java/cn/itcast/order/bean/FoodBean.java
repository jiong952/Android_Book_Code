package cn.itcast.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class FoodBean implements Serializable {
    private static final long serialVersionUID = 1L; //序列化时保持FoodBean类版本的兼容性
    private int foodId;        	  //菜品Id
    private String foodName;   	  //菜品名称
    private String popularity;    //人气
    private String saleNum;    	  //月售量
    private BigDecimal price;	  //价格
    private int count;         	  //添加到购物车中的数量
    private String foodPic;        //菜品图片
    public int getFoodId() {
        return foodId;
    }
    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public String getPopularity() {
        return popularity;
    }
    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }
    public String getSaleNum() {
        return saleNum;
    }
    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getFoodPic() {
        return foodPic;
    }
    public void setFoodPic(String foodPic) {
        this.foodPic = foodPic;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}

