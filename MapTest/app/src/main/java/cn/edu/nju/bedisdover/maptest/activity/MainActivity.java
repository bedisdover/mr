package cn.edu.nju.bedisdover.maptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;

import cn.edu.nju.bedisdover.maptest.listener.LocationListener;
import cn.edu.nju.bedisdover.maptest.MarkerUtil;
import cn.edu.nju.bedisdover.maptest.listener.MyMarkListener;
import cn.edu.nju.bedisdover.maptest.R;
import cn.edu.nju.bedisdover.maptest.listener.TabItemListener;
import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabItemBuilder;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;

public class MainActivity extends AppCompatActivity {

    private AMap map;

    private UiSettings uiSettings;//定义一个UiSettings对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initTab();
        initMap();
    }

    private void initTab() {
//        PagerBottomTabLayout pagerBottomTabLayout = (PagerBottomTabLayout) findViewById(R.id.tab_main);
//
//        //用TabItemBuilder构建一个导航按钮
//        TabItemBuilder tabItemBuilder = new TabItemBuilder(this).create()
//                .setDefaultIcon(android.R.drawable.ic_menu_send)
//                .setText("信息")
//                .setSelectedColor(0xcccccc)
//                .setTag("A")
//                .build();
//
//        //构建导航栏,得到Controller进行后续控制
//        Controller controller = pagerBottomTabLayout.builder()
//                .addTabItem(tabItemBuilder)
//                .addTabItem(android.R.drawable.ic_menu_compass, "位置", 0xcccccc)
//                .addTabItem(android.R.drawable.ic_menu_search, "搜索", 0xeeeeee)
//                .addTabItem(android.R.drawable.ic_menu_help, "帮助", 0xcccccc)
//                .setMode(TabLayoutMode.HIDE_TEXT | TabLayoutMode.CHANGE_BACKGROUND_COLOR)
//                .build();
//
//        controller.addTabItemClickListener(new TabItemListener(this));
    }

    private void initMap() {
//        map = ((SupportMapFragment) getSupportFragmentManager()
//                    .findFragmentById(R.id.map)).getMap();
//
//        map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
//                new LatLng(29.555576, 115.994609),// 庐山风景区
//                12, //新的缩放级别
//                0, //俯仰角0°~45°（垂直与地图时为0）
//                0  ////偏航角 0~360° (正北方为0)
//        )));
//
//        map.addMarkers(MarkerUtil.getMarkerList(this), false);
//
//        map.setOnMarkerClickListener(new MyMarkListener(this));
//
//        uiSettings = map.getUiSettings();
//
//        uiSettings.setCompassEnabled(true);// 显示指南针
//        map.setLocationSource(new LocationListener());// 设置定位监听
//        uiSettings.setMyLocationButtonEnabled(true); // 显示默认的定位按钮
//        map.setMyLocationEnabled(true);// 可触发定位并显示定位层
//        uiSettings.setScaleControlsEnabled(false);//显示比例尺控件
    }
}
