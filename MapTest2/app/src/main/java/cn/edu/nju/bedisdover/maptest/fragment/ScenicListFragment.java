package cn.edu.nju.bedisdover.maptest.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.nju.bedisdover.maptest.MainActivity;
import cn.edu.nju.bedisdover.maptest.R;
import cn.edu.nju.bedisdover.maptest.listener.ContentAdapter;
import cn.edu.nju.bedisdover.maptest.model.Scenic;
import cn.edu.nju.bedisdover.maptest.util.ScenicUtil;

/**
 * Created by song on 16-10-7.
 * <p>
 * 景点列表界面
 */
public class ScenicListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_scenic_list, container, false);

        init(view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        mainActivity = (MainActivity) context;

        super.onAttach(context);
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

        listView.setOnItemClickListener(this);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                Map result = (Map) parent.getItemAtPosition(position);
//                String name = result.get("name").toString();
//
//                System.out.println("ScenicListFragment.onItemClick");
//
//                Intent intent = new Intent(view.getContext(), ScenicActivity.class);
//
//                if (name.equals("黄龙潭")) {
//                    intent = new Intent(view.getContext(), ScenicDemoActivity.class);
//                    if (mainActivity != null) {
//                        mainActivity.jumpToScenic(name);
//                    }
//
//                    return;
//                }
//                intent.putExtra("name", name);
//
//                startActivity(intent);
//            }
//        });
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mainActivity, "listview的item被点击了！，点击的位置是-->" + position,
                Toast.LENGTH_SHORT).show();
    }
}
