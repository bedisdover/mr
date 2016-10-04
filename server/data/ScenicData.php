<?php

/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-10-1
 * Time: 下午8:43
 */
class ScenicData
{
    /**
     * 地球平均半径(km)
     * @var int
     */
    private $radius = 6731;

    /**
     * 视角范围
     * 以正前方为正方向，左右各30度为可见区域，超出30度不可见
     * @var int
     */
    private $perspective = 30;

    /**
     * 获取景点信息
     * @param $scenicName
     * @return null|Scenic
     */
    public function getScenicData($scenicName)
    {
        $connection = new DbConn();
        $conn = $connection->getConnection();

        $sql = "SELECT name, description, longitude, latitude, height " .
            "FROM scenic WHERE name = '$scenicName'";

        $result = $conn->query($sql);
        $row = $result->fetch_assoc();

        if ($row === null) { // 景点信息不存在
            $conn->close();
            return null;
        }

        $point = new Point($row['longitude'], $row['latitude'], $row['height']);

        return new Scenic($scenicName, $row['description'], $point, '');
    }

    public function getNearby($longitude, $latitude, $direction)
    {
        $connection = new DbConn();
        $conn = $connection->getConnection();

        $sql = "SELECT name, description, longitude, latitude, height, " .
                    "CASE WHEN longitude > $longitude AND latitude > $latitude " .
                        "THEN round(degrees(atan(" .
                                            "(longitude - $longitude) * cos(radians(latitude)) / " .
                                            "(latitude - $latitude))), 2) " .
                        "WHEN latitude < $latitude " .
                        "THEN round(180 + degrees(atan(" .
                                                "(longitude - $longitude) * cos(radians(latitude)) / " .
                                                "(latitude - $latitude))), 2) " .
                        "WHEN longitude < $longitude AND latitude > $latitude " .
                        "THEN round(360 + degrees(atan(" .
                                                "(longitude - $longitude) * cos(radians(latitude)) / " .
                                                "(latitude - $latitude))), 2) " .
                        "ELSE NULL END AS azimuth, " .
                        "round(sqrt(pow(cos(radians($latitude)) * cos(radians($longitude)) - " .
                                       "cos(radians(latitude)) * cos(radians(longitude)), 2) + " .
                                   "pow(cos(radians($latitude)) * sin(radians($longitude)) - " .
                                       "cos(radians(latitude)) * sin(radians(longitude)), 2)) * $this->radius, 2) AS distance " .
                "FROM scenic " .
                "HAVING CASE WHEN $direction < $this->perspective " .
                            "THEN azimuth < $this->perspective + $direction OR azimuth > 360 + $direction - $this->perspective " .
                        "WHEN $direction > 360 - $this->perspective " .
                            "THEN azimuth > $direction - $this->perspective OR azimuth < $direction + $this->perspective - 360 " .
                        "ELSE azimuth < $direction + $this->perspective AND azimuth > $direction - $this->perspective END " .
                "ORDER BY distance;";

        $result = $conn->query($sql);

        $array = new ArrayObject();
        while ($row = $result->fetch_assoc()) {
            $point = new Point($row['longitude'], $row['latitude'], $row['height']);
            $scenic = new Scenic($row['name'], $row['description'], $point, '');
            $nearby = new NearbyInfo($scenic, $row['azimuth'], $row['distance']);

            $array->append($nearby);
        }

        $conn->close();

        return $array;
    }
}