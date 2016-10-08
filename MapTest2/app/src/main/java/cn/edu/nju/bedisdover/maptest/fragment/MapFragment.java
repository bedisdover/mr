package cn.edu.nju.bedisdover.maptest.fragment;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.amap.api.maps.model.animation.TranslateAnimation;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.bedisdover.maptest.R;
import cn.edu.nju.bedisdover.maptest.listener.MarkListener;
import cn.edu.nju.bedisdover.maptest.util.MarkerUtil;

/**
 * Created by song on 16-10-7.
 * <p>
 * 地图
 */
public class MapFragment extends Fragment {

    /**
     * 特定的景点名称
     */
    private static String scenicName = "";

    private AMap map;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        MapView mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        initMap(mapView);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        scenicName = "";
    }

    private void initMap(MapView mapView) {
        map = mapView.getMap();
        UiSettings uiSettings = map.getUiSettings();

        map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                new LatLng(29.555576, 115.994609),// 庐山风景区
                12, //新的缩放级别
                0, //俯仰角0°~45°（垂直与地图时为0）
                0  ////偏航角 0~360° (正北方为0)
        )));

        if (scenicName.equals("")) {
            ArrayList<Marker> markerList = map.addMarkers(MarkerUtil.getMarkerList(mapView), false);
            startGrowAnimation(markerList);
        } else {
            Marker marker = map.addMarker(MarkerUtil.getMarkerByName(mapView, scenicName));
//            startJumpAnimation(marker);
        }

        map.setOnMarkerClickListener(new MarkListener(mapView));

        uiSettings.setCompassEnabled(true);// 显示指南针
//        map.setLocationSource(new LocationListener());// 设置定位监听
//        uiSettings.setMyLocationButtonEnabled(true); // 显示默认的定位按钮
        map.setMyLocationEnabled(true);// 可触发定位并显示定位层
        uiSettings.setScaleControlsEnabled(false);//显示比例尺控件
    }

    public static void setScenicName(String scenicName) {
        MapFragment.scenicName = scenicName;
    }

    /**
     * 地上生长的Marker
     */
    private void startGrowAnimation(List<Marker> markerList) {
        if (markerList != null) {
            Animation animation = new ScaleAnimation(0, 1, 0, 1);
            animation.setInterpolator(new LinearInterpolator());
            //整个移动所需要的时间
            animation.setDuration(1500);

            for (Marker growMarker : markerList) {
                //设置动画
                growMarker.setAnimation(animation);
                //开始动画
                growMarker.startAnimation();
            }
        }
    }

    /**
     * 屏幕中心marker 跳动
     */
    public void startJumpAnimation(Marker marker) {
        if (marker != null) {
            //根据屏幕距离计算需要移动的目标点
            final LatLng latLng = marker.getPosition();
            Point point = map.getProjection().toScreenLocation(latLng);
            point.y -= dip2px(getActivity(), 125);
            LatLng target = map.getProjection().fromScreenLocation(point);
            //使用TranslateAnimation,填写一个需要移动的目标点
            Animation animation = new TranslateAnimation(target);
            animation.setInterpolator(new Interpolator() {
                @Override
                public float getInterpolation(float input) {
                    // 模拟重加速度的interpolator
                    if (input <= 0.5) {
                        return (float) (0.5f - 2 * (0.5 - input) * (0.5 - input));
                    } else {
                        return (float) (0.5f - Math.sqrt((input - 0.5f) * (1.5f - input)));
                    }
                }
            });
            //整个移动所需要的时间
            animation.setDuration(600);
            //设置动画
            marker.setAnimation(animation);
            //开始动画
            marker.startAnimation();
        } else {
            Log.e("ama", "marker is null");
        }
    }

    //dip和px转换
    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
