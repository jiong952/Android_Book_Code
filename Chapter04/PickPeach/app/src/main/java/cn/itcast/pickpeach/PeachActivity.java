package cn.itcast.pickpeach;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PeachActivity extends AppCompatActivity implements
        View.OnClickListener {
    private Button btn_one, btn_two, btn_three, btn_four, btn_five,btn_six,btn_exit;
    private int count=0;//桃子个数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peach);
        init();
    }
    private void init() {
        btn_one = findViewById(R.id.btn_one);
        btn_two = findViewById(R.id.btn_two);
        btn_three = findViewById(R.id.btn_three);
        btn_four = findViewById(R.id.btn_four);
        btn_five = findViewById(R.id.btn_five);
        btn_six = findViewById(R.id.btn_six);
        btn_exit = findViewById(R.id.btn_exit);
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        btn_five.setOnClickListener(this);
        btn_six.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_one:    //第一个桃子的点击事件
                info(btn_one);
                break;
            case R.id.btn_two:    //第二个桃子的点击事件
                info(btn_two);
                break;
            case R.id.btn_three:  //第三个桃子的点击事件
                info(btn_three);
                break;
            case R.id.btn_four:   //第四个桃子的点击事件
                info(btn_four);
                break;
            case R.id.btn_five:   //第五个桃子的点击事件
                info(btn_five);
                break;
            case R.id.btn_six:    //第六个桃子的点击事件
                info(btn_six);
                break;
            case R.id.btn_exit:   //“退出桃园”按钮的点击事件
                returnData();
                break;
        }
    }
    /**
     * 按钮的点击事件处理
     */
    private void info(Button btn){
        count++; //桃子个数加1
        btn.setVisibility(View.INVISIBLE);
        Toast.makeText(PeachActivity.this,"摘到"+count+"个桃子",
                Toast.LENGTH_LONG).show();
    }
    /**
     * 将数据回传到上个界面
     */
    private void returnData(){
        Intent intent = new Intent();
        intent.putExtra("count",count);
        setResult(1,intent);
        PeachActivity.this.finish();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0){
            returnData(); //调用数据回传方法
        }
        return false;
    }
}

