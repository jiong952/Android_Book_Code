package cn.itcast.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.itcast.order.R;
import cn.itcast.order.activity.FoodActivity;
import cn.itcast.order.bean.FoodBean;

public class MenuAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoodBean> fbl;                   //菜单列表数据
    private OnSelectListener onSelectListener; //加入购物车按钮的监听事件
    public MenuAdapter(Context context, OnSelectListener onSelectListener) {
        this.mContext = context;
        this.onSelectListener=onSelectListener;
    }
    /**
     * 设置数据更新界面
     */
    public void setData(List<FoodBean> fbl) {
        this.fbl = fbl;
        notifyDataSetChanged();
    }
    /**
     * 获取条目的总数
     */
    @Override
    public int getCount() {
        return fbl == null ? 0 : fbl.size();
    }
    /**
     * 根据position得到对应条目的对象
     */
    @Override
    public FoodBean getItem(int position) {
        return fbl == null ? null : fbl.get(position);
    }
    /**
     * 根据position得到对应条目的Id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * 得到相应position对应的条目视图，position是当前条目的位置，
     * convertView参数是滚出屏幕的条目视图
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        //复用convertView
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.menu_item,
                    null);
            vh.tv_food_name = convertView.findViewById(R.id.tv_food_name);
            vh.tv_popularity =  convertView.findViewById(R.id.tv_popularity);
            vh.tv_sale_num = convertView.findViewById(R.id.tv_sale_num);
            vh.tv_price =  convertView.findViewById(R.id.tv_price);
            vh.btn_add_car = convertView.findViewById(R.id.btn_add_car);
            vh.iv_food_pic =  convertView.findViewById(R.id.iv_food_pic);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取position对应条目的数据对象
        final FoodBean bean = getItem(position);
        if (bean != null) {
            vh.tv_food_name.setText(bean.getFoodName());
            vh.tv_popularity.setText(bean.getPopularity ());
            vh.tv_sale_num.setText("月售" + bean.getSaleNum());
            vh.tv_price.setText("￥"+bean.getPrice());
            Glide.with(mContext)
                    .load(bean.getFoodPic())
                    .error(R.mipmap.ic_launcher)
                    .into(vh.iv_food_pic);
        }
        //每个条目的点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到菜品详情界面
                if (bean == null)return;
                Intent intent = new Intent(mContext,FoodActivity.class);
                //把菜品的详细信息传递到菜品详情界面
                intent.putExtra("food", bean);
                mContext.startActivity(intent);
            }
        });
        vh.btn_add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //加入购物车按钮的点击事件
                onSelectListener.onSelectAddCar(position);
            }
        });
        return convertView;
    }
    class ViewHolder {
        public TextView tv_food_name, tv_popularity, tv_sale_num, tv_price;
        public Button btn_add_car;
        public ImageView iv_food_pic;
    }
    public interface OnSelectListener {
        void onSelectAddCar (int position); //处理加入购物车按钮的方法
    }
}

