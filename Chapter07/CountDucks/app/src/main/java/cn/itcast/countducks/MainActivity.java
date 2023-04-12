package cn.itcast.countducks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyBroadcastReceiverOne one;
    private MyBroadcastReceiverTwo two;
    private MyBroadcastReceiverThree three;
    private ImageView iv_horn;
    private TextView tv_left_content, tv_one, tv_two, tv_three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver();
        init();
    }
    private void init() {
        iv_horn = findViewById(R.id.iv_horn);
        tv_left_content = findViewById(R.id.tv_left_content);
        tv_one = findViewById(R.id.tv_one);
        tv_two = findViewById(R.id.tv_two);
        tv_three = findViewById(R.id.tv_three);
        iv_horn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_left_content.setVisibility(View.VISIBLE);
                iv_horn.setClickable(false);  //设置喇叭图片为不可点击的状态
                Intent intent = new Intent();
                intent.setAction("Count_Ducks");      // 定义广播的事件类型
                sendOrderedBroadcast(intent, null);  // 发送有序广播
//                MyBroadcastReceiverThree receiver = new MyBroadcastReceiverThree();
//                sendOrderedBroadcast(intent,null,receiver, null, 0, null, null); // 发送有序广播
            }
        });
    }
    private void registerReceiver() {
        // 动态注册MyBroadcastReceiverTwo广播
        two = new MyBroadcastReceiverTwo();
        IntentFilter filter2 = new IntentFilter();
        filter2.setPriority(1000);
        filter2.addAction("Count_Ducks");
        registerReceiver(two, filter2);
        // 动态注册MyBroadcastReceiverOne广播
        one = new MyBroadcastReceiverOne();
        IntentFilter filter1 = new IntentFilter();
        filter1.setPriority(1000);
        filter1.addAction("Count_Ducks");
        registerReceiver(one, filter1);
        // 动态注册MyBroadcastReceiverThree广播
        three = new MyBroadcastReceiverThree();
        IntentFilter filter3 = new IntentFilter();
        filter3.setPriority(600);
        filter3.addAction("Count_Ducks");
        registerReceiver(three, filter3);
    }

    private int num=0; // 存放序号的变量
    class MyBroadcastReceiverOne extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv_one.setVisibility(View.VISIBLE);
            num=num+1;
            tv_one.setText(num+"");
            Log.i("BroadcastReceiverOne", "广播接收者One,接收到了广播消息");
            delay();
        }
    }
    class MyBroadcastReceiverTwo extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv_two.setVisibility(View.VISIBLE);
            num=num+1;
            tv_two.setText(num+"");
            Log.i("BroadcastReceiverTwo", "广播接收者Two,接收到了广播消息");
//            abortBroadcast(); //拦截有序广播
//            Log.i("BroadcastReceiverTwo","我是广播接收者Two，广播消息被我拦截了");
            delay();
        }
    }
    class MyBroadcastReceiverThree extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv_three.setVisibility(View.VISIBLE);
            num=num+1;
            tv_three.setText(num + "");
            Log.i("BroadcastReceiverThree", "广播接收者Three,接收到了广播消息");
            delay();
        }
    }
    /**
     * 延迟500毫秒
     */
    private void delay(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(one);
        unregisterReceiver(two);
        unregisterReceiver(three);
    }
}
