package cn.itcast.order.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.order.bean.ShopBean;
import cn.itcast.order.fragment.AdBannerFragment;

public class AdBannerAdapter extends FragmentStatePagerAdapter {
    private List<ShopBean> sbl;
    public AdBannerAdapter(FragmentManager fm) {
        super(fm);
        sbl = new ArrayList<>();
    }
    /**
     *  获取数据并更新界面
     */
    public void setData(List<ShopBean> sbl) {
        this.sbl = sbl;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int index) {
        Bundle args = new Bundle();
        if (sbl.size() > 0)
            args.putSerializable("ad", sbl.get(index % sbl.size()));
        return AdBannerFragment.newInstance(args);
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    /**
     * 返回数据集中元素的数量
     */
    public int getSize() {
        return sbl == null ? 0 : sbl.size();
    }
    @Override
    public int getItemPosition(Object object) {
        //防止刷新结果显示列表的时候出现缓存数据,重载这个函数,使之默认返回POSITION_NONE
        return POSITION_NONE;
    }
}

