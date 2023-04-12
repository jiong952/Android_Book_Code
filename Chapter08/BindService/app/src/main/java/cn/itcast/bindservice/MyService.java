package cn.itcast.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    //创建服务的代理,调用服务中的方法
    class MyBinder extends Binder {
        public void callMethodInService() {
            methodInService();
        }
    }
    public void methodInService() {
        Log.i("MyService", "执行服务中的methodInService()方法");
    }
    @Override
    public void onCreate() {
        Log.i("MyService", "创建服务，执行onCreate()方法");
        super.onCreate();
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("MyService", "绑定服务，执行onBind()方法");
        return new MyBinder();
    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("MyService", "解绑服务，执行onUnbind()方法");
        return super.onUnbind(intent);
    }
}

