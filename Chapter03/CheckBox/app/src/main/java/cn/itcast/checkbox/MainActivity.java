package cn.itcast.checkbox;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener {
    private TextView hobby;
    private String hobbys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化CheckBox控件
        CheckBox shuttlecock = findViewById(R.id.like_shuttlecock);
        CheckBox basketball =  findViewById(R.id.like_basketball);
        CheckBox pingpong = findViewById(R.id.like_pingpong);
        shuttlecock.setOnCheckedChangeListener(this);
        basketball.setOnCheckedChangeListener(this);
        pingpong.setOnCheckedChangeListener(this);
        hobby = findViewById(R.id.hobby);
        hobbys = new String();//存放选中的CheckBox的文本信息
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String motion = buttonView.getText().toString();
        if(isChecked){
            if(!hobbys.contains(motion)){
                hobbys = hobbys + motion;
                hobby.setText(hobbys);
            }
        }else {
            if (hobbys.contains(motion)) {
                hobbys = hobbys.replace(motion, "");
                hobby.setText(hobbys);
            }
        }
    }
}

