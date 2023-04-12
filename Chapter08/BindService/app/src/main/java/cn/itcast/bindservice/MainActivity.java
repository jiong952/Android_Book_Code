package cn.itcast.bindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private MyService.MyBinder myBinder;
    private MyConn myconn;
    private Button btn_bind, btn_call, btn_unbind;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        btn_bind = findViewById(R.id.btn_bind);
        btn_call = findViewById(R.id.btn_call);
        btn_unbind = findViewById(R.id.btn_unbind);
        //设置3个按钮的点击监听事件
        btn_bind.setOnClickListener(this);
        btn_call.setOnClickListener(this);
        btn_unbind.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind:     		 //“绑定服务”按钮点击事件
                if (myconn == null) {
                    myconn = new MyConn(); //创建连接服务的对象
                }
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, myconn, BIND_AUTO_CREATE); //绑定服务
                break;
            case R.id.btn_call:    //“调用服务中的方法”按钮点击事件
                myBinder.callMethodInService(); //调用服务中的方法
                break;
            case R.id.btn_unbind: //“解绑服务”按钮点击事件
                if (myconn != null) {
                    unbindService(myconn); //解绑服务
                    myconn = null;
                }
                break;
        }
    }
    /**
     * 创建MyConn类,用于实现连接服务
     */
    private class MyConn implements ServiceConnection {
        /**
         * 当成功绑定服务时调用的方法,该方法获取MyService中的Ibinder对象
         */
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder)
        {
            myBinder = (MyService.MyBinder) iBinder;
            Log.i("MainActivity", "服务成功绑定, 内存地址为:" + myBinder.toString());
        }
        /**
         * 当服务失去连接时调用的方法
         */
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }
}

