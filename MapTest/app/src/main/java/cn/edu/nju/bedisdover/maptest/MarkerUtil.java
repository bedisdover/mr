package cn.edu.nju.bedisdover.maptest;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by song on 16-10-6.
 * <p>
 * 景点工具类
 */
public class MarkerUtil {

    private MarkerUtil() {
    }

    public static ArrayList<MarkerOptions> getMarkerList(Context context) {
        ArrayList<MarkerOptions> markerList = new ArrayList<>();

        List<PointVO> pointList = getPointList(context);

        for (PointVO pointVO : pointList) {
            MarkerOptions markerOption = new MarkerOptions();
            LatLng location = new LatLng(pointVO.getLatitude(), pointVO.getLongitude());

            markerOption.position(location);
            markerOption.alpha(0);
            markerOption.title(pointVO.getName()).snippet(pointVO.getDescription());

            // 不能拖拽
            markerOption.draggable(false);
            markerOption.icon(BitmapDescriptorFactory.fromBitmap(
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow)));
            // 将Marker设置为贴地显示，可以双指下拉看效果
            markerOption.setFlat(true);

            markerList.add(markerOption);
        }

        return markerList;
    }

    /**
     * 获取景点列表
     */
    public static List<PointVO> getPointList(Context context) {
        List<PointVO> pointList = new ArrayList<>();

        try {
            InputStream inputStream = context.getResources().getAssets().open("data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "\t");

                if (tokenizer.hasMoreTokens()) {
                    String name = tokenizer.nextToken();
                    double longitude = Double.parseDouble(tokenizer.nextToken());
                    double latitude = Double.parseDouble(tokenizer.nextToken());
                    String description = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "";

                    PointVO point = new PointVO(name, longitude, latitude, description);
                    pointList.add(point);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pointList;
    }
}
