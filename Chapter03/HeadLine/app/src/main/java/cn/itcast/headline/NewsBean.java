package cn.itcast.headline;
import java.util.List;
public class NewsBean {
    private int id;                   //新闻id
    private String title;            //新闻标题
    private List<Integer> imgList; //新闻图片
    private String name;             //用户名
    private String comment;         //用户评论
    private String time;             //新闻发布时间
    private int type;                 //新闻类型
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public List<Integer> getImgList() {
        return imgList;
    }
    public void setImgList(List<Integer> imgList) {
        this.imgList = imgList;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
}
