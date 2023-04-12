package cn.itcast.headline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<NewsBean> NewsList;
    public NewsAdapter(Context context,List<NewsBean> NewsList) {
        this.mContext = context;
        this.NewsList=NewsList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                      int viewType) {
        View itemView=null;
        RecyclerView.ViewHolder holder=null;
        if (viewType == 1){
            itemView = LayoutInflater.from(mContext).inflate(R.layout.
                    list_item_one, parent, false);
            holder= new MyViewHolder1(itemView);
        }else if (viewType == 2){
            itemView = LayoutInflater.from(mContext).inflate(R.layout.
                    list_item_two, parent, false);
            holder= new MyViewHolder2(itemView);
        }
        return holder;
    }
    @Override
    public int getItemViewType(int position) {
        return NewsList.get(position).getType();
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,
                                 int position) {
        NewsBean bean=NewsList.get(position);
        if (holder instanceof MyViewHolder1){
            if (position==0) {
                ((MyViewHolder1) holder).iv_top.setVisibility(View.VISIBLE);
                ((MyViewHolder1) holder).iv_img.setVisibility(View.GONE);
            } else {
                ((MyViewHolder1) holder).iv_top.setVisibility(View.GONE);
                ((MyViewHolder1) holder).iv_img.setVisibility(View.VISIBLE);
            }
            ((MyViewHolder1) holder).title.setText(bean.getTitle());
            ((MyViewHolder1) holder).name.setText(bean.getName());
            ((MyViewHolder1) holder).comment.setText(bean.getComment());
            ((MyViewHolder1) holder).time.setText(bean.getTime());
            if (bean.getImgList().size()==0)return;
            ((MyViewHolder1) holder).iv_img.setImageResource(bean.getImgList()
                    .get(0));
        }else if (holder instanceof MyViewHolder2){
            ((MyViewHolder2) holder).title.setText(bean.getTitle());
            ((MyViewHolder2) holder).name.setText(bean.getName());
            ((MyViewHolder2) holder).comment.setText(bean.getComment());
            ((MyViewHolder2) holder).time.setText(bean.getTime());
            ((MyViewHolder2) holder).iv_img1.setImageResource(bean.getImgList()
                    .get(0));
            ((MyViewHolder2) holder).iv_img2.setImageResource(bean.getImgList()
                    .get(1));
            ((MyViewHolder2) holder).iv_img3.setImageResource(bean.getImgList()
                    .get(2));
        }
    }
    @Override
    public int getItemCount() {
        return NewsList.size();
    }
    class MyViewHolder1 extends RecyclerView.ViewHolder {
        ImageView iv_top,iv_img;
        TextView title,name,comment,time;
        public MyViewHolder1(View view) {
            super(view);
            iv_top = view.findViewById(R.id.iv_top);
            iv_img = view.findViewById(R.id.iv_img);
            title = view.findViewById(R.id.tv_title);
            name = view.findViewById(R.id.tv_name);
            comment = view.findViewById(R.id.tv_comment);
            time = view.findViewById(R.id.tv_time);
        }
    }
    class MyViewHolder2 extends RecyclerView.ViewHolder {
        ImageView iv_img1,iv_img2,iv_img3;
        TextView title,name,comment,time;
        public MyViewHolder2(View view) {
            super(view);
            iv_img1 = view.findViewById(R.id.iv_img1);
            iv_img2 = view.findViewById(R.id.iv_img2);
            iv_img3 = view.findViewById(R.id.iv_img3);
            title = view.findViewById(R.id.tv_title);
            name = view.findViewById(R.id.tv_name);
            comment = view.findViewById(R.id.tv_comment);
            time = view.findViewById(R.id.tv_time);
        }
    }
}

