package cn.edu.nju.bedisdover.maptest.fragment;

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
import cn.edu.nju.bedisdover.maptest.listener.LocationListener;

public class MapFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_base_map, container, false);
//
//        MapView mapView = (MapView) view.findViewById(R.id.map_fragment);
         // 在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
//        mapView.onCreate(savedInstanceState);
//
//        initMap(mapView);
//
//        return mapView;
        return inflater.inflate(R.layout.layout_test, container, false);
    }

    private void initMap(MapView mapView) {
        AMap map = mapView.getMap();

        map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                new LatLng(29.555576, 115.994609),// 庐山风景区
                12, //新的缩放级别
                0, //俯仰角0°~45°（垂直与地图时为0）
                0  ////偏航角 0~360° (正北方为0)
        )));

//        map.addMarkers(MarkerUtil.getMarkerList(mainActivity), false);
//
//        map.setOnMarkerClickListener(new MyMarkListener(mainActivity));

        UiSettings uiSettings = map.getUiSettings();

        uiSettings.setCompassEnabled(true);// 显示指南针
        map.setLocationSource(new LocationListener());// 设置定位监听
        uiSettings.setMyLocationButtonEnabled(true); // 显示默认的定位按钮
        map.setMyLocationEnabled(true);// 可触发定位并显示定位层
        uiSettings.setScaleControlsEnabled(false);//显示比例尺控件
    }
}
