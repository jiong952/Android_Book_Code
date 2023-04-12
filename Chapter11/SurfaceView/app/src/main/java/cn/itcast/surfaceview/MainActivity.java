package cn.itcast.surfaceview;
import android.content.ContentResolver;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
public class MainActivity extends AppCompatActivity implements
        SeekBar.OnSeekBarChangeListener, SurfaceHolder.Callback {
    private SurfaceView sv;
    private SurfaceHolder holder;
    private MediaPlayer mediaplayer;
    private RelativeLayout rl;
    private Timer timer;
    private TimerTask task;
    private SeekBar sbar;
    private ImageView play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //去掉默认标题栏
        setContentView(R.layout.activity_main);
        sv =  findViewById(R.id.sv);
        //获取SurfaceView的容器,界面内容是显示在容器中的
        holder = sv.getHolder();
        //setType()为过时的方法,如果4.0以上的系统不写没问题,否则必须要写
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(this);
        rl = findViewById(R.id.rl);
        play = findViewById(R.id.play);
        sbar = findViewById(R.id.sbar);
        sbar.setOnSeekBarChangeListener(this);
        timer = new Timer();//初始化计时器
        task = new TimerTask() {
            @Override
            public void run() {
                if (mediaplayer != null && mediaplayer.isPlaying()) {
                    int total = mediaplayer.getDuration(); //获取视频总时长
                    sbar.setMax(total); //设置视频进度条总时长
                    //获取视频当前进度
                    int progress = mediaplayer.getCurrentPosition();
                    sbar.setProgress(progress); //将当前进度设置给进度条
                } else {
                    play.setImageResource(android.R.drawable.ic_media_play);
                }
            }
        };
        //设置tast任务延迟500毫秒执行,每隔500ms执行一次
        timer.schedule(task, 500, 500);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) { //Surface创建时触发
        try {
            mediaplayer = new MediaPlayer();
            mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC); //音频类型
            Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    getPackageName() + "/" + R.raw.video);//视频路径
            try {
                //设置视频文件路径
                mediaplayer.setDataSource(MainActivity.this, uri);
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, "播放失败",
                        Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            //SurfaceView控件与MediaPlayer类进行关联
            mediaplayer.setDisplay(holder);
            mediaplayer.prepareAsync();     //将视频文件解析到内存中
            mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
            {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaplayer.start(); //播放视频
                }
            });
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "播放失败",
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {//Surface大小发生变化时触发
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) { //Surface注销时触发
        if (mediaplayer.isPlaying()) { //判断视频是否正在播放
            mediaplayer.stop();          //停止视频
        }
    }
    //播放（暂停）按钮的点击事件
    public void click(View view) {
        if (mediaplayer != null && mediaplayer.isPlaying()) { //视频正在播放
            mediaplayer.pause(); //暂停视频播放
            play.setImageResource(android.R.drawable.ic_media_play);
        } else {
            mediaplayer.start(); //开始视频播放
            play.setImageResource(android.R.drawable.ic_media_pause);
        }
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {}//进度发生变化时触发
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { //进度条开始拖动时触发
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { //进度条拖动停止时触发
        int position = seekBar.getProgress(); //获取进度条当前的拖动位置
        if (mediaplayer != null) {
            mediaplayer.seekTo(position); //将进度条的拖动位置设置给MediaPlayer对象
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) { //屏幕触摸事件
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (rl.getVisibility() == View.INVISIBLE) {//进度条和播放按钮不显示
                    rl.setVisibility(View.VISIBLE); //显示进度条和播放按钮
                    //倒计时3秒，3秒后继续隐藏进度条和播放按钮
                    CountDownTimer cdt = new CountDownTimer(3000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            System.out.println(millisUntilFinished);
                        }
                        @Override
                        public void onFinish() {
                            //隐藏进度条和播放按钮
                            rl.setVisibility(View.INVISIBLE);
                        }
                    };
                    cdt.start(); //开启倒计时
                } else if (rl.getVisibility() == View.VISIBLE) {//进度条和播放按钮显示
                    rl.setVisibility(View.INVISIBLE); //隐藏进度条和播放按钮
                }
                break;
        }
        return super.onTouchEvent(event);
    }
    @Override
    protected void onDestroy() {
        task.cancel();            //将TimerTask从任务队列中清除
        timer.cancel();           //将任务队列中的全部任务清除
        timer = null;             //设置对象timer为null
        task = null;              //设置对象task为null
        mediaplayer.release(); //释放MediaPlayer对象占用的资源
        mediaplayer = null;     //将对象mediaplayer设置为null
        super.onDestroy();
    }
}

