package cn.itcast.switches;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btn_switch;
    private ImageView iv_open, iv_close;
    //表示存放开灯与关灯状态的变量，为false时表示关灯状态，为true时表示开灯状态
    private boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        btn_switch = findViewById(R.id.btn_switch);
        iv_open = findViewById(R.id.iv_open);
        iv_close = findViewById(R.id.iv_close);
        btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {//说明此时界面上的图显示的是开灯状态
                    btn_switch.setText("开灯");
                    btn_switch.setBackgroundResource(R.drawable.btn_close);
                    iv_open.setVisibility(View.GONE);    //隐藏开灯按钮图片
                    iv_close.setVisibility(View.VISIBLE);//显示关灯按钮图片
                    isOpen=false;//设置为关灯状态
                    //关闭服务
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    stopService(intent);
                } else { //此时界面上的图显示的是关灯状态
                    btn_switch.setText("关灯");
                    btn_switch.setBackgroundResource(R.drawable.btn_open);
                    iv_open.setVisibility(View.VISIBLE);//显示开灯按钮图片
                    iv_close.setVisibility(View.GONE);  //隐藏关灯按钮图片
                    isOpen=true; //设置为开灯状态
                    //开启服务
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    startService(intent);
                }
            }
        });
    }
}
