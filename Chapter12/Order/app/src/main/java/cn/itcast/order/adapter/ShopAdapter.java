package cn.itcast.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.itcast.order.R;
import cn.itcast.order.activity.ShopDetailActivity;
import cn.itcast.order.bean.ShopBean;

public class ShopAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShopBean> sbl;
    public ShopAdapter(Context context) {
        this.mContext = context;
    }
    /**
     * 获取数据并更新界面
     */
    public void setData(List<ShopBean> sbl) {
        this.sbl = sbl;
        notifyDataSetChanged();
    }
    /**
     * 获取条目的总数
     */
    @Override
    public int getCount() {
        return sbl == null ? 0 : sbl.size();
    }
    /**
     * 根据position得到对应条目的对象
     */
    @Override
    public ShopBean getItem(int position) {
        return sbl == null ? null : sbl.get(position);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        //复用convertView
        if (convertView == null) {
            vh = new ViewHolder();
            convertView=LayoutInflater.from(mContext).inflate(R.layout.shop_item,null);
            vh.tv_shop_name = convertView.findViewById(R.id.tv_shop_name);
            vh.tv_sale_num = convertView.findViewById(R.id.tv_sale_num);
            vh.tv_cost =  convertView.findViewById(R.id.tv_cost);
            vh.tv_feature = convertView.findViewById(R.id.tv_feature);
            vh.tv_time = convertView.findViewById(R.id.tv_time);
            vh.iv_shop_pic = convertView.findViewById(R.id.iv_shop_pic);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取position对应条目的数据对象
        final ShopBean bean = getItem(position);
        if (bean != null) {
            vh.tv_shop_name.setText(bean.getShopName());
            vh.tv_sale_num.setText("月售" + bean.getSaleNum());
            vh.tv_cost.setText("起送￥" + bean.getOfferPrice() + " | 配送￥" +
                    bean.getDistributionCost());
            vh.tv_time.setText(bean.getTime());
            vh.tv_feature.setText(bean.getFeature());
            Glide.with(mContext)
                    .load(bean.getShopPic())
                    .error(R.mipmap.ic_launcher)
                    .into(vh.iv_shop_pic);
        }
        //每个条目的点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到店铺详情界面
                if (bean == null) return;
                Intent intent = new Intent(mContext,ShopDetailActivity.class);
                //把店铺的详细信息传递到店铺详情界面
                intent.putExtra("shop", bean);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder {
        public TextView tv_shop_name, tv_sale_num, tv_cost, tv_feature, tv_time;
        public ImageView iv_shop_pic;
    }
}

