package cn.edu.nju.mr.client.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import java.util.List;

/**
 *
 * Created by alpaca on 16-10-4.
 */
public class LocationAccessor {

    public LocationAccessor(Context context) throws NoLocationAccessException {
        this.mContext = context;
        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        initLocation();
    }

    public void setLocationListener(LocationListener listener) throws NoLocationPermissionException {
        this.mLocationListener = listener;
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            throw new NoLocationPermissionException();
        }
        mLocationManager.requestLocationUpdates(mPreferredProvider, 0, 0, mLocationListener);
    }

    public void stopListen() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        this.mLocationManager.removeUpdates(this.mLocationListener);
    }

    public LocationManager getLocationManager() {
        return mLocationManager;
    }

    public void initLocation() throws NoLocationAccessException {
        List<String> providers = mLocationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            mPreferredProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            mPreferredProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            throw new NoLocationAccessException();
        }
    }

    public Location getLastLocation() throws NoLocationAccessException {
        initLocation();
        if (mLocationManager == null) {
            return null;
        }

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            throw new NoLocationAccessException();
        }
        return mLocationManager.getLastKnownLocation(mPreferredProvider);
    }

    private Context mContext;
    private LocationManager mLocationManager;
    private String mPreferredProvider;
    private LocationListener mLocationListener;
}

