package cn.edu.nju.mr.client.controller;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.nju.mr.client.R;
import cn.edu.nju.mr.client.model.DirectionAccessor;
import cn.edu.nju.mr.client.model.LocationAccessor;
import cn.edu.nju.mr.client.model.NoLocationAccessException;
import cn.edu.nju.mr.client.model.NoLocationPermissionException;

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

    }

    @Override
    protected void onStart() {
        try {
            locationAccessor.setLocationListener(this);
            Location lastLocation = locationAccessor.getLastLocation();
            txtLocation.setText(String.format("Longitude:%f Latitude:%f", lastLocation.getLongitude(), lastLocation.getLatitude()));
        } catch (NoLocationAccessException e) {
            Toast.makeText(this, "没有打开定位", Toast.LENGTH_SHORT).show();
        } catch (NoLocationPermissionException e) {
            Toast.makeText(this, "没有获取定位的权限", Toast.LENGTH_SHORT).show();
        }

        directionAccessor.registerListener(this);
        super.onStart();
    }

    @Override
    protected void onPause() {
        directionAccessor.unregisterListener(this);
        super.onPause();
    }

    //--------------direction services--------------------
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int dir = (int) sensorEvent.values[0];

        new AlertDialog.Builder(this).setMessage(dir + "").show();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    //-------------------location services-------------------------
    @Override
    public void onLocationChanged(Location location) {
        txtLocation.setText(String.format("Longitude:%f Latitude:%f", location.getLongitude(), location.getLatitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

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

    private void initComponents() {
        txtLocation = (TextView) findViewById(R.id.txtLocation);
    }
    private LocationAccessor locationAccessor;
    private TextView txtLocation;
    private DirectionAccessor directionAccessor;
}
