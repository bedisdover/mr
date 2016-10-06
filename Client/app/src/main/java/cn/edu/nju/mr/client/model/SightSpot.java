package cn.edu.nju.mr.client.model;

import cn.edu.nju.mr.client.dao.LocationDao;
import cn.edu.nju.mr.client.vo.ScreenLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by alpaca on 16-10-4.
 */
public class SightSpot {

    public static final double LATITUDE_UNIT = 111319;    // 纬度长度/度

    public SightSpot(String name, double latitude, double longitude, double altitude) {
        setAltitude(altitude);
        setLatitude(latitude);
        setLongitude(longitude);
        setName(name);
    }

    public static List<SightSpot> getNearBySpotList(double longitude, double latitude, int direction) {
        LocationDao dao = new LocationDao(longitude, latitude, direction);
        List<SightSpot> result = new ArrayList<>();
        Map p = dao.readData();
        p = (Map) p.get("object");
        for (Object key: p.keySet()) {
            Map tmp = (Map) ((Map) p.get(key)).get("scenic");
            String name = tmp.get("name").toString();
            String description = tmp.get("description").toString();
            double longi = Double.valueOf(((Map) tmp.get("location")).get("longitude").toString());
            double latit = Double.valueOf(((Map) tmp.get("location")).get("latitude").toString());
            double alti = Double.valueOf(((Map) tmp.get("location")).get("height").toString());
            String knowledgeLink = tmp.get("knowledgeLink").toString();
            SightSpot spot = new SightSpot(name, latit, longi, alti);
            spot.setDesciption(description);
            spot.setKnowledgeLink(knowledgeLink);
            result.add(spot);
        }
        return result;
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

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getKnowledgeLink() {
        return knowledgeLink;
    }

    public void setKnowledgeLink(String knowledgeLink) {
        this.knowledgeLink = knowledgeLink;
    }

    public String getName() {
        return name;
    }

    private String name;
    private double altitude;
    private double longitude, latitude;
    private String desciption;
    private String knowledgeLink;
}
