package cn.edu.nju.bedisdover.maptest.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.nju.bedisdover.maptest.R;
import cn.edu.nju.bedisdover.maptest.ScenicActivity;
import cn.edu.nju.bedisdover.maptest.model.Scenic;
import cn.edu.nju.bedisdover.maptest.util.ScenicUtil;

/**
 * Created by song on 16-10-7.
 * <p>
 * 景点列表界面
 */
public class ScenicListFragment extends Fragment {

    /**
     * 标记是否已经初始化
     */
    private boolean initial = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenic_list, container, false);

        init(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void init(final View view) {
        ListView listView = (ListView) view.findViewById(R.id.scenic_list);

        SimpleAdapter adapter = new SimpleAdapter(view.getContext(),
                getData(view), R.layout.list_scenic,
                new String[]{"icon", "name"},
                new int[]{R.id.scenic_icon, R.id.scenic_name});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Map result = (Map) parent.getItemAtPosition(position);

                Intent intent = new Intent(view.getContext(), ScenicActivity.class);
                intent.putExtra("name", result.get("name").toString());

                startActivity(intent);
            }
        });
    }

    private List<Map<String, Object>> getData(View view) {
        System.out.println("ScenicListFragment.getData");
        List<Map<String, Object>> list = new ArrayList<>();

        List<Scenic> scenicList = ScenicUtil.getScenicList(view);

        for (Scenic scenic : scenicList) {
            Map<String, Object> map = new HashMap<>();

            map.put("icon", R.drawable.scenic_icon);
            map.put("name", scenic.getName());

            list.add(map);
        }

        initial = true;

        return list;
    }
}
