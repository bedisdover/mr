package cn.edu.nju.mr.client.controller;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.nju.mr.client.R;
import cn.edu.nju.mr.client.model.*;
import cn.edu.nju.mr.client.util.ScenicUtil;
import cn.edu.nju.mr.client.vo.ScreenLocation;

import java.io.IOException;
import java.util.List;

public class NavigatorActivity extends AppCompatActivity implements LocationListener, SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        initComponents();


        scenicList = ScenicUtil.getScenicList(new View(this));
        scenicList.add(new Scenic("1", 10, 29, ""));
        scenicList.add(new Scenic("2", 100, 29, ""));
        scenicList.add(new Scenic("3", 50, 179, ""));



        try {
            locationAccessor = new LocationAccessor(this);
        } catch (NoLocationAccessException e) {
            e.printStackTrace();
        }
        directionAccessor = new DirectionAccessor(this);
        cameraAccessor = new CameraAccessor(this, surfaceView);

        initServices();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 获取定位和方向信息
        initServices();
    }


    private void initServices() {
        try {
            locationAccessor.setLocationListener(this);
            Location lastLocation = locationAccessor.getLastLocation();
            if (lastLocation == null) {
                txtLocation.setText("无法获取位置");
            }
            if (lastLocation != null && txtLocation != null)
                txtLocation.setText(String.format("Longitude:%f Latitude:%f", lastLocation.getLongitude(), lastLocation.getLatitude()));
        } catch (NoLocationAccessException e) {
            Toast.makeText(this, "没有打开定位", Toast.LENGTH_SHORT).show();
        } catch (NoLocationPermissionException e) {
            Toast.makeText(this, "没有获取定位的权限", Toast.LENGTH_SHORT).show();
        }

        directionAccessor.registerListener(this);

        // 打开摄像头并显示预览
        startCamera();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        initServices();
    }

    @Override
    protected void onPause() {
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
            Toast.makeText(this, "没有打开定位", Toast.LENGTH_SHORT).show();
        } catch (NoLocationPermissionException e) {
            Toast.makeText(this, "没有获取定位的权限", Toast.LENGTH_SHORT).show();
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

    public void addNewButton(Scenic scenic, double longitude, double latitude, int direction) {
        Button b = new Button(this);
        b.setText(scenic.getName());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        windowManager = getWindowManager();
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
                    Toast.makeText(NavigatorActivity.this, "无法打开相机", Toast.LENGTH_SHORT).show();
                } catch (NoAvailableCameraException e) {
                    Toast.makeText(NavigatorActivity.this, "没有可用的相机", Toast.LENGTH_SHORT).show();
                }
            }
        }, 1000);
    }

    private void initComponents() {
        txtLocation = (TextView) findViewById(R.id.txtLocation);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        button = (Button) findViewById(R.id.button);
        mainPane = (RelativeLayout) findViewById(R.id.mainPane);
    }

    private WindowManager windowManager;
    private LocationAccessor locationAccessor;
    private TextView txtLocation;
    private SurfaceView surfaceView;
    private DirectionAccessor directionAccessor;
    private CameraAccessor cameraAccessor;
    private Button button;
    private RelativeLayout mainPane;
    private Handler handler = new Handler();
    private List<Scenic> scenicList;
    private double longitude = 0, latitude = 0;
    private int direction;
}
