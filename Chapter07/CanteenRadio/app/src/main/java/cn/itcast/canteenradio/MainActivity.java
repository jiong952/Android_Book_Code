package cn.itcast.canteenradio;
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
    private ImageView iv_horn;
    private TextView tv_left_content,tv_right_content;
    private MyBroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        // 获取界面控件
        iv_horn=findViewById(R.id.iv_horn);
        tv_left_content=findViewById(R.id.tv_left_content);
        tv_right_content=findViewById(R.id.tv_right_content);
        receiver = new MyBroadcastReceiver();      // 实例化广播接收者
        String action = "Open_Rice";
        IntentFilter intentFilter = new IntentFilter(action);
        registerReceiver(receiver, intentFilter); //注册广播接收者
        iv_horn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_right_content.setText("开饭啦！");
                Intent intent = new Intent();
                // 定义广播的事件类型
                intent.setAction("Open_Rice");
                sendBroadcast(intent);     //发送广播
            }
        });
    }
    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("Open_Rice")){
                tv_left_content.setVisibility(View.VISIBLE);
                Log.i("MyBroadcastReceiver", "自定义的广播接收者,接收到了发送开饭信号的广播消息");
            }
            Log.i("MyBroadcastReceiver", intent.getAction());
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);    // 注销注册的广播接收者
    }
}
