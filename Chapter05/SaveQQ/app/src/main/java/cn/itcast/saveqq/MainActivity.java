package cn.itcast.saveqq;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_account;   //账号输入框
    private EditText et_password;  //密码输入框
    private Button btn_login;       //登录按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //通过工具类FileSaveQQ中的getUserInfo()方法获取QQ账号和密码信息
//        Map<String, String> userInfo = FileSaveQQ.getUserInfo(this);
        Map<String, String> userInfo = SPSaveQQ.getUserInfo(this);
        if (userInfo != null) {
            et_account.setText(userInfo.get("account"));   //将获取的账号显示到界面上
            et_password.setText(userInfo.get("password")); //将获取的密码显示到界面上
        }
    }
    private void initView() {
        et_account =  findViewById(R.id.et_account);
        et_password =  findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        //设置按钮的点击监听事件
        btn_login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //当点击登录按钮时，获取界面上输入的QQ账号和密码
                String account = et_account.getText().toString().trim();
                String password = et_password.getText().toString();
                //检验输入的账号和密码是否为空
                if (TextUtils.isEmpty(account)) {
                    Toast.makeText(this, "请输入QQ账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                //保存用户信息
//                boolean isSaveSuccess = FileSaveQQ.saveUserInfo(this, account,password);
                boolean isSaveSuccess = SPSaveQQ.saveUserInfo(this, account, password);
                if (isSaveSuccess) {
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
