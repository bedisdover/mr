<?php
include "../../dao/ScenicDao.php";
include "../../data/ScenicData.php";
include "../../model/Scenic.php";
include "../../model/NearbyInfo.php";
include "../../model/Point.php";
include "../../model/Message.php";
include "../../util/DbConn.php";

/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-10-3
 * Time: 下午5:54
 *
 * 附近景点信息
 */
$longitude = $_GET['longitude'];
$latitude = $_GET['latitude'];
$direction = $_GET['direction'];

if ($longitude === '' || $latitude === '' || $direction === '') {
    echo json_encode(new Message(0, "请求参数错误", null));
} else {
    echo json_encode(ScenicDao::getNearby($longitude, $latitude, $direction));
}

