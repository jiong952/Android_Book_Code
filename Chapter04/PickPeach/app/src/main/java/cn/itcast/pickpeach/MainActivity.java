package cn.itcast.pickpeach;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn_peach;
    private TextView tv_count;
    private int totalCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        btn_peach = findViewById(R.id.btn_peach);
        tv_count = findViewById(R.id.tv_count);
        btn_peach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PeachActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1&&resultCode==1) {
            int count = data.getIntExtra("count", 0); //获取回传的数据
            totalCount = totalCount + count;
            tv_count.setText("摘到" + totalCount + "个");
        }
    }
}

