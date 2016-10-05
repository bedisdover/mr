package cn.edu.nju.mr.client.model;

import android.location.Location;

/**
 * Created by alpaca on 16-10-4.
 */
public class SightSpot {

    public static final double LATITUDE_UNIT = 111000;    // 纬度长度/度

    public SightSpot(String name, double latitude, double longitude, double altitude) {
        setAltitude(altitude);
        setLatitude(latitude);
        setLongitude(longitude);
        setName(name);
    }

    /**
     * 返回两点之间的距离。
     *
     * @param another 需要计算的另一点
     * @return 长度（米）
     */
    public double getDistance(SightSpot another) {
        double x = (getLatitude() - another.getLatitude()) * LATITUDE_UNIT;
        double y = (getLongitude() - another.getLongitude()) * getLongitudeUnit(getLatitude());
        return Math.sqrt(x * x + y * y);
    }

    public double getLongitudeUnit(double latitude) {
        return Math.cos(latitude / 180 * 3.1415926535) * LATITUDE_UNIT;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    private String name;
    private double altitude;
    private double longitude, latitude;
}
