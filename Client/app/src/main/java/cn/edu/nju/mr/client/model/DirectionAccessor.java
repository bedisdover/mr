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
        initDirection();
    }

    public void registerListener(SensorEventListener listener) {
        mSensorManager.registerListener(listener, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_FASTEST);
        //mSensorManager.registerListener(listener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
    }

    public SensorManager getmSensorManager() {
        return mSensorManager;
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
