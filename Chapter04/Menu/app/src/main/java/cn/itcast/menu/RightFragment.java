package cn.itcast.menu;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.io.Serializable;
import java.util.List;
public class RightFragment extends Fragment {
    private ListView lv_list;
    public RightFragment() {
    }
    public RightFragment getInstance(List<FoodBean> list) {
        RightFragment rightFragment = new RightFragment();
        //通过Bundle对象传递数据可以保证在设备横竖屏切换时传递的数据不丢失
        Bundle bundle = new Bundle();
        //将需要传递的字符串以键值对的形式传入bundle对象
        bundle.putSerializable("list", (Serializable) list);
        rightFragment.setArguments(bundle);
        return rightFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_layout, container, false);
        lv_list = view.findViewById(R.id.lv_list);
        if (getArguments() != null) {
            List<FoodBean> list = (List<FoodBean>) getArguments().
                    getSerializable("list");
            RightAdapter adapter = new RightAdapter(getActivity(), list);
            lv_list.setAdapter(adapter);
        }
        return view;
    }
}

