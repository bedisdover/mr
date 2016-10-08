package cn.edu.nju.bedisdover.maptest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.nju.bedisdover.maptest.R;

/**
 * Created by song on 16-10-8.
 * <p>
 * 景点列表项
 */
public class ScenicListItemFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenic_list_item, container, false);

        final TextView scenicName = (TextView) view.findViewById(R.id.list_scenic_name);
        scenicName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showScenicInfo(scenicName.getText().toString());
            }
        });

        ImageView go = (ImageView) view.findViewById(R.id.list_scenic_go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInMap(scenicName.getText().toString());
            }
        });

        return view;
    }

    public void showScenicInfo(String scenicName) {
        System.out.println("ScenicListItemFragment.showScenicInfo");

    }

    public void showInMap(String scenicName) {

        System.out.println("ScenicListItemFragment.showInMap");
    }
}
