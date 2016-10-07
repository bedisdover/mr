package cn.edu.nju.bedisdover.maptest.model;

/**
 * Created by song on 16-10-7.
 *
 * 景点对象
 */
public class Scenic {

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

    @Override
    public String toString() {
        return "name: " + name + ", longitude = " + longitude + ", latitude = " + latitude
                + ", description = " + description;
    }
}
