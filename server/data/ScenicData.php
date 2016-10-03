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
     * 获取景点信息
     * @param $scenicName
     * @return null|Scenic
     */
    public function getScenicData($scenicName)
    {
        $connection = new DbConn();
        $conn = $connection->getConnection();

        $sql = "SELECT name, description, x(location), y(location), height FROM points WHERE name = '$scenicName'";

        $result = $conn->query($sql);
        $row = $result->fetch_assoc();

        if ($row === null) { // 景点信息不存在
            $conn->close();
            return null;
        }

        $point = new Point($row['x(location)'], $row['y(location)'], $row['height']);

        return new Scenic($scenicName, $row['description'], $point, '');
    }

    public function getNearBy($scenicName)
    {
        $connection = new DbConn();
        $conn = $connection->getConnection();

        $sql = "SELECT lng,lat," .
            "(POWER(MOD(ABS(lng - $lng),360),2) + POWER(ABS(lat - $lat),2)) AS distance" .
            "FROM `user_location`" .
            "ORDER BY distance LIMIT 100";

        $result = $conn->query($sql);
        $row = $result->fetch_assoc();

        if ($row === null) { // 景点信息不存在
            $conn->close();
            return null;
        }
    }
}