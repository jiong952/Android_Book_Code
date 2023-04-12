package cn.itcast.menu;
import java.io.Serializable;
public class FoodBean implements Serializable {
    //序列化时保持FoodBean类版本的兼容性
    private static final long serialVersionUID = 1L;
    private String name;	//菜品名称
    private String sales;	//月售信息
    private String price;	//菜品价格
    private int img;			//菜品图片
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSales() {
        return sales;
    }
    public void setSales(String sales) {
        this.sales = sales;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public int getImg() {
        return img;
    }
    public void setImg(int img) {
        this.img = img;
    }
}

