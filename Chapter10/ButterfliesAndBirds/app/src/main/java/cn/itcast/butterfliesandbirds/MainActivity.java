package cn.itcast.butterfliesandbirds;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {
    private int screenWidth;
    private ImageView iv_butterfly,iv_bird;
    private AnimationDrawable animation;
    private AnimatorSet flyAnimatorSet;
    private ObjectAnimator objectAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        getWindowWidth();
        iv_butterfly=findViewById(R.id.iv_butterfly);
        iv_bird=findViewById(R.id.iv_bird);
        flyAnimation(1); //实现蝴蝶飞舞的效果
        flyAnimation(2); //实现小鸟飞舞的效果
    }
    /**
     * 获取屏幕宽度
     */
    private void getWindowWidth(){
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth= dm.widthPixels;
    }
    /**
     * 实现飞舞的效果
     */
    private void flyAnimation(int flag){
        flyAnimatorSet=new AnimatorSet();
        if (flag==1) {
            //获取逐帧动画
            animation= (AnimationDrawable) iv_butterfly.getBackground();
            //设置蝴蝶在水平方向移动的距离为屏幕的宽度-270
            objectAnimator = ObjectAnimator.ofFloat(iv_butterfly,
                    "translationX", screenWidth - 270);
            objectAnimator.setDuration(3*1000);//设置动画时间为3秒
        }else if (flag==2){
            //获取逐帧动画
            animation= (AnimationDrawable) iv_bird.getBackground();
            //设置小鸟在水平方向移动的距离为屏幕的宽度
            objectAnimator = ObjectAnimator.ofFloat(iv_bird,
                    "translationX", screenWidth);
            objectAnimator.setRepeatCount(Animation.RESTART); //重新开始播放动画
            objectAnimator.setRepeatCount(Animation.INFINITE);//循环播放动画
            objectAnimator.setDuration(10*1000);//设置动画时间为10秒
        }
        objectAnimator.setInterpolator(new LinearInterpolator());//设置线性插值器
        flyAnimatorSet.play(objectAnimator);
        animation.start();     //开启逐帧动画
        flyAnimatorSet.start();//开启属性动画
    }
}
