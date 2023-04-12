package cn.itcast.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class RightAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoodBean> list;
    public RightAdapter(Context context ,List<FoodBean> list) {
        this.mContext = context;
        this.list=list;
    }
    @Override
    public int getCount() {		//获取列表条目的总数
        return list.size();		//返回ListView 条目的总数
    }
    @Override
    public Object getItem(int position) {
        return list.get(position); //返回列表条目的数据对象
    }
    @Override
    public long getItemId(int position) {
        return position; //返回列表条目的id
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.list_item, null);
            holder = new ViewHolder();
            holder.tv_name =  convertView.findViewById(R.id.tv_name);
            holder.tv_sale =  convertView.findViewById(R.id.tv_sale);
            holder.tv_price =  convertView.findViewById(R.id.tv_price);
            holder.iv_img =  convertView.findViewById(R.id.iv_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FoodBean bean=list.get(position);
        holder.tv_name.setText(bean.getName());
        holder.tv_sale.setText(bean.getSales());
        holder.tv_price.setText(bean.getPrice());
        holder.iv_img.setBackgroundResource(bean.getImg());
        return convertView;
    }
    class ViewHolder {
        TextView tv_name, tv_sale,tv_price;
        ImageView iv_img;
    }
}
