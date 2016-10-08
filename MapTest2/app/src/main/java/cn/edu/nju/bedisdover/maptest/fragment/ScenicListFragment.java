package cn.edu.nju.bedisdover.maptest.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.nju.bedisdover.maptest.R;
import cn.edu.nju.bedisdover.maptest.listener.ContentAdapter;
import cn.edu.nju.bedisdover.maptest.model.Scenic;
import cn.edu.nju.bedisdover.maptest.util.ScenicUtil;

/**
 * Created by song on 16-10-7.
 * <p>
 * 景点列表界面
 */
public class ScenicListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenic_list, container, false);

        init(view);

        return view;
    }

    private void init(final View view) {
        ListView listView = (ListView) view.findViewById(R.id.scenic_list);
        listView.setDividerHeight(2);

//        SimpleAdapter adapter = new SimpleAdapter(view.getContext(),
//                getData(view), R.layout.fragment_scenic_list_item,
//                new String[]{"icon", "name", "go"},
//                new int[]{R.id.list_scenic_icon, R.id.list_scenic_name, R.id.list_scenic_go});
//        listView.setAdapter(adapter);
        listView.setAdapter(new ContentAdapter(view.getContext(), getData(view)));
    }

    /**
     * 获取列表数据
     */
    private List<Map<String, ?>> getData(View view) {
        List<Map<String, ?>> list = new ArrayList<>();

        List<Scenic> scenicList = ScenicUtil.getScenicList(view);

        for (Scenic scenic : scenicList) {
            Map<String, Object> map = new HashMap<>();

            map.put("icon", R.drawable.scenic_icon);
            map.put("name", scenic.getName());
            map.put("go", R.drawable.go);

            list.add(map);
        }

        return list;
    }
}
