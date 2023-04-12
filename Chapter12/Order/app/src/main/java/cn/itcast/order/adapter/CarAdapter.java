package cn.itcast.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import cn.itcast.order.R;
import cn.itcast.order.bean.FoodBean;

public class CarAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoodBean> fbl;
    private OnSelectListener onSelectListener;
    public CarAdapter(Context context, OnSelectListener onSelectListener) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.car_item, null);
            vh.tv_food_name =  convertView.findViewById(R.id.tv_food_name);
            vh.tv_food_count = convertView.findViewById(R.id.tv_food_count);
            vh.tv_food_price =  convertView.findViewById(R.id.tv_food_price);
            vh.iv_add =  convertView.findViewById(R.id.iv_add);
            vh.iv_minus = convertView.findViewById(R.id.iv_minus);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取position对应的条目数据对象
        final FoodBean bean = getItem(position);
        if (bean != null) {
            vh.tv_food_name.setText(bean.getFoodName());
            vh.tv_food_count.setText(bean.getCount()+"");
            BigDecimal count=BigDecimal.valueOf(bean.getCount());
            vh.tv_food_price.setText("￥" + bean.getPrice().multiply(count));
        }
        vh.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectListener.onSelectAdd(position,vh.tv_food_count,vh.
                        tv_food_price);
            }
        });
        vh.iv_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectListener.onSelectMis(position,vh.tv_food_count,vh.
                        tv_food_price);
            }
        });
        return convertView;
    }
    class ViewHolder {
        public TextView tv_food_name, tv_food_count,tv_food_price;
        public ImageView iv_add,iv_minus;
    }
    public interface OnSelectListener {
        void onSelectAdd(int position,TextView tv_food_price,TextView tv_food_count);
        void onSelectMis(int position,TextView tv_food_price,TextView tv_food_count);

    }
}

