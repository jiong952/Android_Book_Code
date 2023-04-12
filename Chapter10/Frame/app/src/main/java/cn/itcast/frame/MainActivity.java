package cn.itcast.frame;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private ImageView iv_wifi;
    private Button btn_start;
    private AnimationDrawable animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_wifi = findViewById(R.id.iv_wifi);
        btn_start = findViewById(R.id.btn_play);
        btn_start.setOnClickListener(this);
        //获取AnimationDrawable对象
        animation = (AnimationDrawable) iv_wifi.getBackground();
    }
    @Override
    public void onClick(View v) {
        if (!animation.isRunning()) { //如果动画当前没有播放
            animation.start();//播放动画
            btn_start.setBackgroundResource(android.R.drawable.ic_media_pause);
        } else {
            animation.stop(); //停止动画
            btn_start.setBackgroundResource(android.R.drawable.ic_media_play);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(animation.isRunning()){
            animation.stop();
        }
        iv_wifi.clearAnimation();
    }
}

