<?php
include "util/DbConn.php";

/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-10-6
 * Time: 上午10:37
 */

$db = new DbConn();
$conn = $db->getConnection();

$file = fopen("data.txt", "r") or exit("fail to open file <br/>");

while (!feof($file)) {
    $str = fgetss($file);
    $str_token = strtok($str, "\t");

    $name = $str_token;
    $longitude = $str_token = strtok("\t");
    $latitude = $str_token = strtok("\t");
    $temp = PHP_INT_MIN;

    $sql = <<<EOF
        INSERT INTO scenic VALUES ('$name', '', $longitude, $latitude, $temp);
EOF;

    $result = $conn->query($sql);

    if ($result === true) {
        echo "name = $name, result = success <br/>";
    } else {
        echo "name = $name, error = $conn->error<br/>";
    }
}

fclose($file);
