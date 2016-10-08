package cn.edu.nju.mr.client.model;

import cn.edu.nju.mr.client.vo.ScreenLocation;

/**
 * Created by song on 16-10-7.
 *
 * 景点对象
 */
public class Scenic {


    public static final double LATITUDE_UNIT = 111319;    // 纬度长度/度

    private final String name;

    private final double longitude, latitude;

    private final String description;

    public Scenic(String name, double longitude, double latitude, String description) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 当用户处于某个位置时，这个景点将要显示在屏幕的什么位置上。
     *
     * @param longitude 用户当前所处的纬度
     * @param latitude 用户当前所处的经度
     * @param direction 用户当前面朝的方向
     * @return 屏幕的位置信息。
     * @see ScreenLocation
     */
    public ScreenLocation getDisplayLocation(double longitude, double latitude, int direction) {
        double dx = (longitude - this.longitude) * getLongitudeUnit(this.latitude);
        double dy = (latitude - this.latitude) * LATITUDE_UNIT;
        double valTan = dy / dx;
        double absoluteAngel = Math.atan(valTan) / 3.1415926 * 180;
        if (Math.abs(absoluteAngel - direction) > 30) {
            return null;
        }
        float ay = 0.5f;
        float ax = ((float) absoluteAngel - direction) / 30 * 0.5f + 0.5f;
        return new ScreenLocation(ax, ay);
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

    @Override
    public String toString() {
        return "name: " + name + ", longitude = " + longitude + ", latitude = " + latitude
                + ", description = " + description;
    }
}
