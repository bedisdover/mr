package cn.edu.nju.bedisdover.maptest.fragment;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.bedisdover.maptest.MainActivity;
import cn.edu.nju.bedisdover.maptest.R;
import cn.edu.nju.bedisdover.maptest.ScenicActivity;
import cn.edu.nju.bedisdover.maptest.model.CameraAccessor;
import cn.edu.nju.bedisdover.maptest.model.DirectionAccessor;
import cn.edu.nju.bedisdover.maptest.model.LocationAccessor;
import cn.edu.nju.bedisdover.maptest.model.NoAvailableCameraException;
import cn.edu.nju.bedisdover.maptest.model.NoLocationAccessException;
import cn.edu.nju.bedisdover.maptest.model.NoLocationPermissionException;
import cn.edu.nju.bedisdover.maptest.model.Scenic;
import cn.edu.nju.bedisdover.maptest.util.ScenicUtil;
import cn.edu.nju.bedisdover.maptest.vo.ScreenLocation;

import static android.R.attr.direction;

/**
 * Created by song on 16-10-8.
 */

public class NavigatorFragment extends Fragment implements LocationListener, SensorEventListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigator, container, false);
    }

    @Override
    public void onStart() {
        // 获取定位和方向信息
        initComponents();


        scenicList = new ArrayList<>();
        scenicList.add(new Scenic("白鹿洞书院", 10, 29, ""));
        scenicList.add(new Scenic("庐山会议旧址", 100, 129, ""));
        scenicList.add(new Scenic("五老峰", 50, 179, ""));

        scenicList.add(new Scenic("三叠泉", 140, 43, ""));
        scenicList.add(new Scenic("如琴湖", 53, 2, ""));

        scenicList.add(new Scenic("芦林湖", 234, 23, ""));
        scenicList.add(new Scenic("龙首崖", 45, 35, ""));

        scenicList.add(new Scenic("黄龙潭", 234, 55, ""));
        scenicList.add(new Scenic("美庐别墅", 234, 65, ""));



        try {
            locationAccessor = new LocationAccessor(getActivity());
        } catch (NoLocationAccessException e) {
            e.printStackTrace();
        }
        directionAccessor = new DirectionAccessor(getActivity());
        cameraAccessor = new CameraAccessor(getActivity(), surfaceView);

        initServices();

        super.onStart();
    }


    private void initServices() {
        try {
            locationAccessor.setLocationListener(this);
        }catch (NoLocationPermissionException e) {
            Toast.makeText(getActivity(), "没有获取定位的权限", Toast.LENGTH_SHORT).show();
        }

        directionAccessor.registerListener(this);

        // 打开摄像头并显示预览
        startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        directionAccessor.unregisterListener(this);
        cameraAccessor.stopCamera();
        locationAccessor.stopListen();
    }

    //--------------direction services--------------------
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        this.direction = (int) sensorEvent.values[0];
        flushDisplay();
    }

    float[] acc;
    float[] mag;

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        /* do nothing */
    }

    //-------------------location services-------------------------
    @Override
    public void onLocationChanged(Location location) {
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        /* do nothing */
    }

    @Override
    public void onProviderEnabled(String s) {
        try {
            locationAccessor.initLocation();
            locationAccessor.setLocationListener(this);
        } catch (NoLocationAccessException e) {
            Toast.makeText(getActivity(), "没有打开定位", Toast.LENGTH_SHORT).show();
        } catch (NoLocationPermissionException e) {
            Toast.makeText(getActivity(), "没有获取定位的权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProviderDisabled(String s) {
        /*do nothing*/
    }

    public void flushDisplay() {
        mainPane.removeAllViews();
        for (Scenic scenic: scenicList) {
            addNewButton(scenic, longitude, latitude, direction);
        }
    }

    public void addNewButton(final Scenic scenic, double longitude, double latitude, int direction) {
        Button b = new Button(getActivity());
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScenicActivity.class);
                intent.putExtra("name", scenic.getName());
                getActivity().startActivity(intent);
            }
        });
        b.setText(scenic.getName() + "\n"+ (int) scenic.getLatitude() +"km");
        b.setBackgroundResource(R.color.greeeeen);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        windowManager = getActivity().getWindowManager();
        ScreenLocation location = scenic.getDisplayLocation(longitude, latitude, direction);
        if (location == null) return;
        lp.leftMargin = (int) (windowManager.getDefaultDisplay().getWidth() * location.getPx());
        lp.topMargin = (int) (windowManager.getDefaultDisplay().getHeight() * location.getPy());
        b.setLayoutParams(lp);
        mainPane.addView(b);
    }

    private void startCamera() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    cameraAccessor.startCamera();
                } catch (IOException e) {
                    Toast.makeText(getActivity(), "无法打开相机", Toast.LENGTH_SHORT).show();
                } catch (NoAvailableCameraException e) {
                    Toast.makeText(getActivity(), "没有可用的相机", Toast.LENGTH_SHORT).show();
                }
            }
        }, 1000);
    }

    private void initComponents() {
        mainPane = (RelativeLayout) findViewById(R.id.mainPane);
    }

    public View findViewById(int resId) {
        return getView().findViewById(resId);
    }

    private WindowManager windowManager;
    private LocationAccessor locationAccessor;
    private SurfaceView surfaceView;
    private DirectionAccessor directionAccessor;
    private CameraAccessor cameraAccessor;
    private RelativeLayout mainPane;
    private Handler handler = new Handler();
    private List<Scenic> scenicList;
    private double longitude = 0, latitude = 0;
    private int direction;
}
