<?php

include "../../dao/ScenicDao.php";
include "../../data/ScenicData.php";
include "../../model/Scenic.php";
include "../../model/Point.php";
include "../../model/Message.php";
include "../../util/DbConn.php";


/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-9-18
 * Time: 下午2:57
 *
 * 景点信息
 */
$scenicName = urldecode($_GET['name']);

if ($scenicName === '') {
    echo json_encode(new Message(0, "请求参数错误", null));
} else {
    // To protect javaScript injection
    $scenicName = htmlspecialchars($scenicName);

    // To protect MySQL injection
    $scenicName = stripslashes($scenicName);

    $scenicInfo = ScenicDao::getScenicInfo($scenicName);

    echo json_encode($scenicInfo);
}