package cn.itcast.order.activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.math.BigDecimal;
import java.util.List;
import cn.itcast.order.R;
import cn.itcast.order.adapter.OrderAdapter;
import cn.itcast.order.bean.FoodBean;
public class OrderActivity extends AppCompatActivity {
    private ListView lv_order;
    private OrderAdapter adapter;
    private List<FoodBean> carFoodList;
    private TextView tv_title, tv_back,tv_distribution_cost,tv_total_cost,
            tv_cost,tv_payment;
    private RelativeLayout rl_title_bar;
    private BigDecimal money,distributionCost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //获取购物车中的数据
        carFoodList= (List<FoodBean>) getIntent().getSerializableExtra(
                "carFoodList");
        //获取购物车中菜的总价格
        money=new BigDecimal(getIntent().getStringExtra("totalMoney"));
        //获取店铺的配送费
        distributionCost=new BigDecimal(getIntent().getStringExtra(
                "distributionCost"));
        initView();
        setData();
    }
    /**
     * 初始化界面控件
     */
    private void initView(){
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("订单");
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.
                blue_color));
        tv_back =  findViewById(R.id.tv_back);
        lv_order= findViewById(R.id.lv_order);
        tv_distribution_cost = findViewById(R.id.tv_distribution_cost);

        tv_total_cost =  findViewById(R.id.tv_total_cost);
        tv_cost =  findViewById(R.id.tv_cost);
        tv_payment =  findViewById(R.id.tv_payment);
        // 返回键的点击事件
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //“去支付”按钮的点击事件
                Dialog dialog = new Dialog(OrderActivity.this, R.style.
                        Dialog_Style);
                dialog.setContentView(R.layout.qr_code);
                dialog.show();
            }
        });
    }
    /**
     * 设置界面数据
     */
    private void setData() {
        adapter=new OrderAdapter(this);
        lv_order.setAdapter(adapter);
        adapter.setData(carFoodList);
        tv_cost.setText("￥"+money);
        tv_distribution_cost.setText("￥"+distributionCost);
        tv_total_cost.setText("￥"+(money.add(distributionCost)));
    }
}
