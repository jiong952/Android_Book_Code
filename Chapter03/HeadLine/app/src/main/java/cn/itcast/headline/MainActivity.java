package cn.itcast.headline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] titles = {"各地餐企齐行动，杜绝餐饮浪费",
            "花菜有人焯水，有人直接炒，都错了，看饭店大厨如何做",
            "睡觉时，双脚突然蹬一下，有踩空感，像从高楼坠落，是咋回事？",
            "实拍外卖小哥砸开小吃店的卷帘门救火，灭火后淡定继续送外卖",
            "还没成熟就被迫提前采摘，8毛一斤却没人要，果农无奈：不摘不行",
            "大会、大展、大赛一起来，北京电竞“好嗨哟”"};
    private String[] names = {"央视新闻客户端", "味美食记", "民富康健康", "生活小记",
            "禾木报告", "燕鸣"};
    private String[] comments = {"9884评", "18评", "78评", "678评", "189评",
            "304评"};
    private String[] times = {"6小时前", "刚刚", "1小时前", "2小时前", "3小时前",
            "4个小时前"};
    private int[] icons1 = {R.drawable.food, R.drawable.takeout,
            R.drawable.e_sports};
    private int[] icons2 = {R.drawable.sleep1, R.drawable.sleep2, R.drawable.sleep3,
            R.drawable.fruit1,R.drawable.fruit2, R.drawable.fruit3};
    //新闻类型，1表示置顶新闻或只有1张图片的新闻，2表示包含3张图片的新闻
    private int[] types = {1, 1, 2, 1, 2, 1};
    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private List<NewsBean> NewsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        mRecyclerView = findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NewsAdapter(MainActivity.this, NewsList);
        mRecyclerView.setAdapter(mAdapter);
    }
    private void setData() {
        NewsList = new ArrayList<NewsBean>();
        NewsBean bean;
        for (int i = 0; i < titles.length; i++) {
            bean = new NewsBean();
            bean.setId(i + 1);
            bean.setTitle(titles[i]);
            bean.setName(names[i]);
            bean.setComment(comments[i]);
            bean.setTime(times[i]);
            bean.setType(types[i]);
            switch (i) {
                case 0: //置顶新闻的图片设置
                    List<Integer> imgList0 = new ArrayList<>();
                    bean.setImgList(imgList0);
                    break;
                case 1://设置第2个条目的图片数据
                    List<Integer> imgList1 = new ArrayList<>();
                    imgList1.add(icons1[i - 1]);
                    bean.setImgList(imgList1);
                    break;
                case 2://设置第3个条目的图片数据
                    List<Integer> imgList2 = new ArrayList<>();
                    imgList2.add(icons2[i - 2]);
                    imgList2.add(icons2[i - 1]);
                    imgList2.add(icons2[i]);
                    bean.setImgList(imgList2);
                    break;
                case 3://设置第4个条目的图片数据
                    List<Integer> imgList3 = new ArrayList<>();
                    imgList3.add(icons1[i - 2]);
                    bean.setImgList(imgList3);
                    break;
                case 4://设置第5个条目的图片数据
                    List<Integer> imgList4 = new ArrayList<>();
                    imgList4.add(icons2[i - 1]);
                    imgList4.add(icons2[i]);
                    imgList4.add(icons2[i + 1]);
                    bean.setImgList(imgList4);
                    break;
                case 5://设置第6个条目的图片数据
                    List<Integer> imgList5 = new ArrayList<>();
                    imgList5.add(icons1[i - 3]);
                    bean.setImgList(imgList5);
                    break;
            }
            NewsList.add(bean);
        }
    }
}

