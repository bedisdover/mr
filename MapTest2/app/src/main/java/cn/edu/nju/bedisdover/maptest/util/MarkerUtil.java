package cn.edu.nju.bedisdover.maptest.util;

import android.graphics.BitmapFactory;
import android.view.View;

import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.bedisdover.maptest.R;
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

        for (Scenic scenic : scenicList) {
            MarkerOptions markerOptions = getMarkerOptions(scenic);

            markerOptions.icon(
                    BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(view.getResources(), R.drawable.scenic_icon)));

            markerList.add(markerOptions);
        }

        return markerList;
    }

    public static MarkerOptions getMarkerByName(View view, String name) {
        Scenic scenic = ScenicUtil.getScenicByName(name);


        MarkerOptions markerOptions = getMarkerOptions(scenic);
        markerOptions.anchor(0.5f,0.5f).icon(
                BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(view.getResources(), R.drawable.scenic_location)));

        return markerOptions;
    }

    private static MarkerOptions getMarkerOptions(Scenic scenic) {
        MarkerOptions markerOption = new MarkerOptions();
        LatLng location = new LatLng(scenic.getLatitude(), scenic.getLongitude());

        markerOption.position(location);
        markerOption.title(scenic.getName());
        markerOption.infoWindowEnable(true);
        // 不能拖拽
        markerOption.draggable(false);

        return markerOption;
    }
}
