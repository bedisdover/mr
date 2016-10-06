package cn.edu.nju.mr.client.dao;

import cn.edu.nju.mr.client.util.HttpUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;

/**
 * Created by alpaca on 16-10-5.
 */
public class LocationDao extends DaoBase {

    public LocationDao(double longitude, double latitude, int direction) {
        setLongitude(longitude);
        setLatitude(latitude);
        setDirection(direction);
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public String getAction() {
        return "/controller/scenic/nearby.php";
    }

    @Override
    public String getFullUrl() {
        return DaoBase.Host + getAction();
    }

    @Override
    public String sendRequest() {
        String result = HttpUtil.sendGet(getFullUrl() + String.format(Locale.CHINA, "?longitude=%f&latitude=%f&direction=%d", longitude, latitude, direction));
        try {
            return URLDecoder.decode(result, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new LocationDao(116.037644, 29.525714, 360).sendRequest());
    }

    private double longitude = 0.0, latitude = 0.0;
    private int direction = 0;
}
