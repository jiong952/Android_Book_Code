package cn.itcast.radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.rdg);
        textView = findViewById(R.id.tv);
        //利用setOnCheckedChangeListener()为RadioGroup设置监听事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //判断被点击的是哪一个RadioButton控件
                if (checkedId == R.id.rbtn) {
                    textView.setText("您的性别是：男");
                } else {
                    textView.setText("您的性别是：女");
                }
            }
        });
    }
}
