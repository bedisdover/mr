package cn.edu.nju.bedisdover.maptest.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.nju.bedisdover.maptest.MainActivity;
import cn.edu.nju.bedisdover.maptest.R;
import cn.edu.nju.bedisdover.maptest.model.Scenic;
import cn.edu.nju.bedisdover.maptest.util.ScenicUtil;

/**
 * Created by song on 16-10-8.
 */

public class TestFragment extends Fragment {

    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        init(view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mainActivity = (MainActivity) context;

        FragmentManager manager = mainActivity.getSupportFragmentManager();
    }

    private void init(final View view) {
        List<Scenic> scenicList = ScenicUtil.getScenicList(view);

    }

    /**
     * 获取列表数据
     */
    private List<Map<String, Object>> getData(View view) {
        List<Map<String, Object>> list = new ArrayList<>();

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
