package cn.itcast.pinduoduo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private GoodsAdapter adapter;             // 列表的适配器
    public static final int MSG_GOODS_OK = 1; // 获取数据
    private MHandler mHandler;
    // 内网接口
    public static final String WEB_SITE = "http://172.16.43.20:8080/goods";
    // 商品列表接口
    public static final String REQUEST_GOODS_URL = "/goods_list_data.json";
    private RecyclerView rv_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new MHandler();
        init();
        initData();
    }
    private void init() {
        rv_list = findViewById(R.id.rv_list);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        rv_list.setLayoutManager(manager);
        adapter = new GoodsAdapter(MainActivity.this);
        rv_list.setAdapter(adapter);
    }
    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(WEB_SITE +
                REQUEST_GOODS_URL).build();
        Call call = okHttpClient.newCall(request);
        // 开启异步线程访问网络
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string(); // 获取商品数据
                Message msg = new Message();
                msg.what = MSG_GOODS_OK;
                msg.obj = res;
                mHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Call call, IOException e) {
            }
        });
    }
    /**
     * 事件捕获
     */
    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_GOODS_OK:
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        // 解析获取的JSON数据
                        List<GoodsInfo> goodsInfos = getGoodsList(vlResult);
                        adapter.setData(goodsInfos);
                    }
                    break;
            }
        }
    }
    public List<GoodsInfo> getGoodsList(String json) {
        Gson gson = new Gson(); // 使用gson库解析JSON数据
        // 创建一个TypeToken的匿名子类对象，并调用对象的getType()方法
        Type listType = new TypeToken<List<GoodsInfo>>(){}.getType();
        // 把获取到的集合数据存放到goodsInfos中
        List<GoodsInfo> goodsInfos = gson.fromJson(json, listType);
        return goodsInfos;
    }
}
