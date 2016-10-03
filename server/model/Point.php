<?php

/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-10-1
 * Time: 下午8:35
 *
 * 位置点对象
 */
class Point
{
    /**
     * 经度、纬度、高程
     */
    public $longitude, $latitude, $height;

    /**
     * Point constructor.
     * @param $longitude
     * @param $latitude
     * @param $height
     */
    public function __construct($longitude, $latitude, $height)
    {
        $this->longitude = $longitude;
        $this->latitude = $latitude;
        $this->height = $height;
    }

    /**
     * @return mixed
     */
    public function getLongitude()
    {
        return $this->longitude;
    }

    /**
     * @param mixed $longitude
     */
    public function setLongitude($longitude)
    {
        $this->longitude = $longitude;
    }

    /**
     * @return mixed
     */
    public function getLatitude()
    {
        return $this->latitude;
    }

    /**
     * @param mixed $latitude
     */
    public function setLatitude($latitude)
    {
        $this->latitude = $latitude;
    }

    /**
     * @return mixed
     */
    public function getHeight()
    {
        return $this->height;
    }

    /**
     * @param mixed $height
     */
    public function setHeight($height)
    {
        $this->height = $height;
    }
}