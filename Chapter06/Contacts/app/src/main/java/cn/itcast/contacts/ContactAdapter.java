package cn.itcast.contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter
        .MyViewHolder>  {
    private Context mContext;
    private List<ContactInfo> contactInfoList;
    public ContactAdapter(Context context, List<ContactInfo> contactInfoList){
        this.mContext=context;
        this.contactInfoList=contactInfoList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(
                LayoutInflater.from(mContext).inflate(
                        R.layout.contact_item, parent, false));
        return holder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(contactInfoList.get(position).getContactName());
        holder.tv_phone.setText(contactInfoList.get(position).getPhoneNumber());
    }
    @Override
    public int getItemCount() {
        return contactInfoList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_phone;
        ImageView iv_photo;
        public MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_phone = view.findViewById(R.id.tv_phone);
            iv_photo = view.findViewById(R.id.iv_photo);
        }
    }
}

