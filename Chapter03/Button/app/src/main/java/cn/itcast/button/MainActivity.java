package cn.itcast.button;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btn_one, btn_two, btn_three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_one = findViewById(R.id.btn_one);
        btn_two = findViewById(R.id.btn_two);
        btn_three = findViewById(R.id.btn_three);
        btn_three.setOnClickListener(this);
        //实现按钮1的点击
        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //按钮2的点击事件
                btn_one.setText("按钮1已被点击");
            }
        });
    }
    /*
     *实现按钮2的点击
     */
    public void click(View view) {
        btn_two.setText("按钮2已被点击");
    }
    /*
     *实现按钮3的点击
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_three:     //按钮3的点击事件
                btn_three.setText("按钮3已被点击");
                break;
        }
    }
}

