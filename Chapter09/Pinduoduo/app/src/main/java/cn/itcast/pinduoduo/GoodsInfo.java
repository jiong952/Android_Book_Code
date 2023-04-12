package cn.itcast.pinduoduo;
public class GoodsInfo {
    private int id;             // 商品id
    private String count;      // 已砍商品的数量
    private String goodsName; // 商品名称
    private String goodsPic;  // 商品图片
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public String getGoodsPic() {
        return goodsPic;
    }
    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }
}
