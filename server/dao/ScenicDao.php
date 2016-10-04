<?php

/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-10-1
 * Time: 下午8:41
 */
class ScenicDao
{
    /**
     * ScenicDao constructor.
     */
    private function __construct()
    {
    }

    /**
     * 获得景点信息
     *
     * @param string $scenicName
     * 景点名称
     * @return Message
     * 返回信息，包含{状态码，状态信息，景点信息对象}
     */
    public static function getScenicInfo($scenicName)
    {
        $scenicData = new ScenicData();
        $scenicInfo = $scenicData->getScenicData($scenicName);

        if ($scenicInfo === null) {
            return new Message(0, '景点不存在', null);
        } else {
            return new Message(1, $scenicName, $scenicInfo);
        }
    }

    public static function getNearby($longitude, $latitude, $direction)
    {
        $scenicData = new ScenicData();
        $result = $scenicData->getNearby((double)$longitude, (double)$latitude, (double)$direction);

        if ($result === null) {
            return new Message(0, "", null);
        } else {
            return new Message(1, "", $result);
        }
    }
}