package cn.edu.nju.bedisdover.maptest.util;

import android.view.View;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.bedisdover.maptest.model.Scenic;

/**
 * Created by song on 16-10-6.
 * <p>
 * 景点工具类
 */
public class MarkerUtil {

    private MarkerUtil() {
    }

    public static ArrayList<MarkerOptions> getMarkerList(View view) {
        ArrayList<MarkerOptions> markerList = new ArrayList<>();

        List<Scenic> scenicList = ScenicUtil.getScenicList(view);

        for (Scenic Scenic : scenicList) {
            MarkerOptions markerOption = new MarkerOptions();
            LatLng location = new LatLng(Scenic.getLatitude(), Scenic.getLongitude());

            markerOption.position(location);
            markerOption.alpha(0);
            markerOption.title(Scenic.getName()).snippet(Scenic.getDescription());

            // 不能拖拽
            markerOption.draggable(false);
            // 将Marker设置为贴地显示，可以双指下拉看效果
            markerOption.setFlat(true);

            markerList.add(markerOption);
        }

        return markerList;
    }
}
