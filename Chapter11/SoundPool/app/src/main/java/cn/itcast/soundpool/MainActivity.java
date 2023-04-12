package cn.itcast.soundpool;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private SoundPool soundpool;
    private HashMap<Integer,Integer> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化界面控件，并为控件添加点击事件的监听器
        ImageView iv_do = findViewById(R.id.iv_do);
        ImageView iv_re = findViewById(R.id.iv_re);
        ImageView iv_mi = findViewById(R.id.iv_mi);
        ImageView iv_fa = findViewById(R.id.iv_fa);
        ImageView iv_so = findViewById(R.id.iv_so);
        ImageView iv_la = findViewById(R.id.iv_la);
        ImageView iv_si = findViewById(R.id.iv_si);
        iv_do.setOnClickListener(this);
        iv_re.setOnClickListener(this);
        iv_mi.setOnClickListener(this);
        iv_fa.setOnClickListener(this);
        iv_so.setOnClickListener(this);
        iv_la.setOnClickListener(this);
        iv_si.setOnClickListener(this);
        initSoundPool();//初始化SoundPool
    }
    private void initSoundPool() {
        if(soundpool == null){
            //创建SoundPool对象
            soundpool = new SoundPool(7, AudioManager.STREAM_SYSTEM, 0);
        }
        //加载音频文件，并将文件存储到HashMap集合中
        map.put(R.id.iv_do,soundpool.load(this,R.raw.music_do,1));
        map.put(R.id.iv_re,soundpool.load(this,R.raw.music_re,1));
        map.put(R.id.iv_mi,soundpool.load(this,R.raw.music_mi,1));
        map.put(R.id.iv_fa,soundpool.load(this,R.raw.music_fa,1));
        map.put(R.id.iv_so,soundpool.load(this,R.raw.music_so,1));
        map.put(R.id.iv_la,soundpool.load(this,R.raw.music_la,1));
        map.put(R.id.iv_si,soundpool.load(this,R.raw.music_si,1));
    }
    @Override
    public void onClick(View v) {
        play(v.getId());
    }
    private void play(int i){
        soundpool.play(map.get(i),1.0f,1.0f,0,0,1.0f); //播放音频
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundpool != null) {
            soundpool.autoPause(); //暂停播放音频
            soundpool.release();   //释放Soundpool对象占用的资源
            soundpool = null;
        }
    }
}

