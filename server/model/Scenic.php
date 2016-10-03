<?php

/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-10-1
 * Time: 下午8:31
 *
 * 景点对象
 */
class Scenic
{
    /**
     * 名称, 描述, 位置(经度、纬度、高程), 地理知识链接
     */
    public $name, $description, $location, $knowledgeLink;

    /**
     * Scenic constructor.
     * @param $name
     * @param $description
     * @param $location
     * @param $knowledgeLink
     */
    public function __construct($name, $description, $location, $knowledgeLink)
    {
        $this->name = urlencode($name);
        $this->description = urlencode($description);
        $this->location = $location;
        $this->knowledgeLink = $knowledgeLink;
    }

    /**
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * @param string $name
     */
    public function setName($name)
    {
        $this->name = $name;
    }

    /**
     * @return array
     */
    public function getKnowledgeLink()
    {
        return $this->knowledgeLink;
    }

    /**
     * @param array $knowledgeLink
     */
    public function setKnowledgeLink($knowledgeLink)
    {
        $this->knowledgeLink = $knowledgeLink;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return Point
     */
    public function getLocation()
    {
        return $this->location;
    }

    /**
     * @param Point $location
     */
    public function setLocation($location)
    {
        $this->location = $location;
    }
}