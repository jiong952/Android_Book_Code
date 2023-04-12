package cn.itcast.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private EditText et_name, et_email, et_pwd;
    private Button btn_submit;
    private String name, email, pwd, sex, hobbys;
    private RadioGroup rg_sex;
    private CheckBox cb_sing,cb_dance,cb_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        //获取界面控件
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_pwd = findViewById(R.id.et_pwd);
        rg_sex = findViewById(R.id.rg_sex);
        cb_sing = findViewById(R.id.cb_sing);
        cb_dance = findViewById(R.id.cb_dance);
        cb_read = findViewById(R.id.cb_read);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);//设置提交按钮的点击事件的监听器
        //设置复选框控件的点击事件的监听器
        cb_sing.setOnCheckedChangeListener(this);
        cb_dance.setOnCheckedChangeListener(this);
        cb_read.setOnCheckedChangeListener(this);
        hobbys=new String();
        //设置单选按钮的点击事件
        rg_sex.setOnCheckedChangeListener(new RadioGroup.
                OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){ //判断被点击的RadioButton
                    case R.id.rb_boy:
                        sex = "男";
                        break;
                    case R.id.rb_girl:
                        sex = "女";
                        break;
                }
            }
        });
    }
    /**
     * 获取界面输入的信息
     */
    private void getData() {
        name = et_name.getText().toString().trim();
        email = et_email.getText().toString().trim();
        pwd = et_pwd.getText().toString().trim();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit: //提交按钮的点击事件
                getData();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(MainActivity.this, "请输入名字",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "请输入邮箱",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(MainActivity.this, "请输入密码",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(sex)) {
                    Toast.makeText(MainActivity.this, "请选择性别",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(hobbys)) {
                    Toast.makeText(MainActivity.this, "请选择兴趣爱好",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "注册成功",
                            Toast.LENGTH_SHORT).show();
                    Log.i("MainActivity","注册的用户信息："+"名字："+name+", 邮箱："
                            +email+", 性别："+sex+", 兴趣爱好："+hobbys);
                }
                break;
        }
    }
    /**
     * 复选框的点击事件
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String motion = buttonView.getText().toString();//获取复选框中的内容
        if (isChecked) {
            if (!hobbys.contains(motion)) { //判断之前选择的内容是否与此次选择的不一样
                hobbys = hobbys + motion;
            }
        } else {
            if (hobbys.contains(motion)) {
                hobbys = hobbys.replace(motion, "");
            }
        }
    }
}
