package cn.itcast.videoview;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private VideoView videoView;
    private MediaController controller;
    ImageView iv_play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoview);
        iv_play = findViewById(R.id.iv_play);
        //资源文件夹下的视频文件路径
        String url = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(url);    //字符串url解析成Uri
        videoView.setVideoURI(uri); //设置videoview的播放资源
        //为VideoView控件绑定控制器
        controller = new MediaController(this);
        videoView.setMediaController(controller);
        iv_play.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_play:
                iv_play.setVisibility(View.GONE);
                play();
                break;
        }
    }
    private void play() {
        videoView.start();// 播放视频
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                iv_play.setVisibility(View.VISIBLE);
                iv_play.setImageResource(android.R.drawable.ic_media_play);
            }
        });
    }
}
