package cn.edu.nju.mr.client.controller;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.location.LocationListener;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.nju.mr.client.R;
import cn.edu.nju.mr.client.model.*;
import cn.edu.nju.mr.client.vo.ScreenLocation;

import java.io.IOException;

public class NavigatorActivity extends AppCompatActivity implements LocationListener, SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        initComponents();

        try {
            this.locationAccessor = new LocationAccessor(this);
        } catch (NoLocationAccessException e) {
            e.printStackTrace();
        }
        this.directionAccessor = new DirectionAccessor(this);
        this.cameraAccessor = new CameraAccessor(this, surfaceView);
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
        int dir = (int) sensorEvent.values[0];
        txtLocation.setText(dir + "");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        /* do nothing */
    }

    //-------------------location services-------------------------
    @Override
    public void onLocationChanged(Location location) {
        txtLocation.setText(String.format("Longitude:%f Latitude:%f", location.getLongitude(), location.getLatitude()));
        Toast.makeText(this, location.getLongitude() + " " + location.getLatitude(), Toast.LENGTH_LONG).show();
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

    public void addNewButton(ScreenLocation location) {
        Button b = new Button(this);
        b.setText("顾狗子");
        b.setHeight(100);
        b.setWidth(100);
        mainPane.addView(b);
        b.setX(100);
        b.setY(300);
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

    private LocationAccessor locationAccessor;
    private TextView txtLocation;
    private SurfaceView surfaceView;
    private DirectionAccessor directionAccessor;
    private CameraAccessor cameraAccessor;
    private Button button;
    private RelativeLayout mainPane;
    private Handler handler = new Handler();
}
