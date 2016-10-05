package cn.edu.nju.mr.client.model;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by alpaca on 16-10-5.
 */
public class DirectionAccessor {

    public DirectionAccessor(Context context) {
        this.mContext = context;
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
    }

    public void registerListener(SensorEventListener listener) {
        mSensorManager.registerListener(listener, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void unregisterListener(SensorEventListener listener) {
        mSensorManager.unregisterListener(listener);
    }

    private void initDirection() {
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
    }

    private SensorManager mSensorManager;
    private Context mContext;
}
