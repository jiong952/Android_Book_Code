package cn.itcast.tween;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonFour;
    private ImageView ivBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件并为对应的控件加添点击事件的监听器
        buttonOne = findViewById(R.id.btn_one);
        buttonTwo = findViewById(R.id.btn_two);
        buttonThree = findViewById(R.id.btn_three);
        buttonFour = findViewById(R.id.btn_four);
        ivBean = findViewById(R.id.iv_bean);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one: //"渐变"按钮的点击事件
                Animation alpha = AnimationUtils.loadAnimation(this,
                        R.anim.alpha_animation);
                ivBean.startAnimation(alpha);
                break;
            case R.id.btn_two: //"旋转"按钮的点击事件
                Animation rotate = AnimationUtils.loadAnimation(this,
                        R.anim.rotate_animation);
                ivBean.startAnimation(rotate);
                break;
            case R.id.btn_three://"缩放"按钮的点击事件
                Animation scale = AnimationUtils.loadAnimation(this,
                        R.anim.scale_animation);
                ivBean.startAnimation(scale);
                break;
            case R.id.btn_four: //"平移"按钮的点击事件
                Animation translate = AnimationUtils.loadAnimation(this,
                        R.anim.translate_animation);
                ivBean.startAnimation(translate);
                break;
        }
    }
}
