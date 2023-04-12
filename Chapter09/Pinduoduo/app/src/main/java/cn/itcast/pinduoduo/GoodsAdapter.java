package cn.itcast.pinduoduo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
public class GoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<GoodsInfo> GoodsList = new ArrayList<>();
    public GoodsAdapter(Context context) {
        this.mContext = context;
    }
    /**
     * 获取数据更新界面
     */
    public void setData(List<GoodsInfo> GoodsList) {
        this.GoodsList = GoodsList;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View itemView = null;
        RecyclerView.ViewHolder holder = null;
        itemView = LayoutInflater.from(mContext).inflate(R.layout.goods_item, parent, false);
        holder = new MyViewHolder(itemView);
        return holder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GoodsInfo bean = GoodsList.get(position);
        ((MyViewHolder)holder).tv_count.setText("已砍" + bean.getCount() + "件");
        ((MyViewHolder)holder).tv_goods_name.setText(bean.getGoodsName());
        Glide.with(mContext)
                .load(bean.getGoodsPic())
                .error(R.mipmap.ic_launcher)
                .into(((MyViewHolder)holder).iv_img);
    }
    @Override
    public int getItemCount() {
        return GoodsList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_count, tv_goods_name;
        ImageView iv_img;
        Button btn_free;
        public MyViewHolder(View view) {
            super(view);
            tv_count = view.findViewById(R.id.tv_count);
            tv_goods_name = view.findViewById(R.id.tv_goods_name);
            iv_img = view.findViewById(R.id.iv_img);
            btn_free = view.findViewById(R.id.btn_free);
        }
    }
}

