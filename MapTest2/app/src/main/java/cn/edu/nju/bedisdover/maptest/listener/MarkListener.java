package cn.edu.nju.bedisdover.maptest.listener;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.model.Marker;

import cn.edu.nju.bedisdover.maptest.ScenicActivity;
import cn.edu.nju.bedisdover.maptest.ScenicDemoActivity;

/**
 * Created by song on 16-10-7.
 * <p>
 * 景点点击监听事件
 */
public class MarkListener implements OnMarkerClickListener {

    private View view;

    public MarkListener(View view) {
        this.view = view;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        String name = marker.getTitle();

        Intent intent = new Intent(view.getContext(), ScenicActivity.class);

        if (name.equals("黄龙泉")) {
            intent = new Intent(view.getContext(), ScenicDemoActivity.class);
        }
        intent.putExtra("name", name);

        view.getContext().startActivity(intent);

        return false;
    }
}
