<?php

/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-10-4
 * Time: 下午10:59
 *
 * 附近景点对象
 */
class NearbyInfo
{
    /**
     * 景区
     */
    public $scenic;

    /**
     * 方位角
     * 正北方向为0°，顺时针递增
     */
    public $azimuth;

    /**
     * 距离
     */
    public $distance;

    /**
     * NearbyInfo constructor.
     * @param $scenic
     * @param $azimuth
     * @param $distance
     */
    public function __construct($scenic, $azimuth, $distance)
    {
        $this->scenic = $scenic;
        $this->azimuth = $azimuth;
        $this->distance = $distance;
    }

    /**
     * @return mixed
     */
    public function getScenic()
    {
        return $this->scenic;
    }

    /**
     * @param mixed $scenic
     */
    public function setScenic($scenic)
    {
        $this->scenic = $scenic;
    }

    /**
     * @return mixed
     */
    public function getAzimuth()
    {
        return $this->azimuth;
    }

    /**
     * @param mixed $azimuth
     */
    public function setAzimuth($azimuth)
    {
        $this->azimuth = $azimuth;
    }

    /**
     * @return mixed
     */
    public function getDistance()
    {
        return $this->distance;
    }

    /**
     * @param mixed $distance
     */
    public function setDistance($distance)
    {
        $this->distance = $distance;
    }
}