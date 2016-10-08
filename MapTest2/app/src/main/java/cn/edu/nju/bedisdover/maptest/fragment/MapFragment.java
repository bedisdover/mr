package cn.edu.nju.bedisdover.maptest.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;

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
    private String scenicName;

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
    public void onAttach(Context context) {

        super.onAttach(context);
        Intent intent = ((Activity) context).getIntent();
        String name = intent.getStringExtra("scenicName");

        if (name != null) {
            scenicName = name;
        } else {
            scenicName = "";
        }

    }

    private void initMap(MapView mapView) {
        AMap map = mapView.getMap();
        UiSettings uiSettings = map.getUiSettings();

        map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                new LatLng(29.555576, 115.994609),// 庐山风景区
                12, //新的缩放级别
                0, //俯仰角0°~45°（垂直与地图时为0）
                0  ////偏航角 0~360° (正北方为0)
        )));

        if (scenicName.equals("")) {
            map.addMarkers(MarkerUtil.getMarkerList(mapView), false);
        } else {
            map.addMarker(MarkerUtil.getMarkerByName(mapView, scenicName));
        }

        map.setOnMarkerClickListener(new MarkListener(mapView));

        uiSettings.setCompassEnabled(true);// 显示指南针
//        map.setLocationSource(new LocationListener());// 设置定位监听
//        uiSettings.setMyLocationButtonEnabled(true); // 显示默认的定位按钮
        map.setMyLocationEnabled(true);// 可触发定位并显示定位层
        uiSettings.setScaleControlsEnabled(false);//显示比例尺控件
    }
}
