<?php

/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-9-17
 * Time: 下午9:06
 */
class UserData
{
    public function add()
    {

    }

    public function getUserData($userName)
    {
        $connection = new Connect();
        $conn = $connection->getInstance();

        $sql = "SELECT (userName, password) from user where userName = $userName;";

        $result = $conn->query($sql);
    }
}