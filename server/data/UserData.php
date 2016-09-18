<?php

/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-9-17
 * Time: 下午9:06
 */
class UserData
{
    public function __construct()
    {
    }

    public function addNewUser($userName, $password)
    {
        if ($this->getUserData($userName) != null) {
            return "用户名已存在";
        }

        $dbConn = new DbConn();
        $conn = $dbConn->getConnection();

        $sql = "INSERT INTO user(userName, password) VALUES ('$userName', password('$password'));";

        if ($conn->query($sql) === true) {
            $info = "用户注册成功";
        } else {
            $info = "用户注册失败";
        }

        $conn->close();
        return $info;
    }

    /**
     * 验证用户信息
     * @param $userName
     * @param $password
     * @return bool 验证通过返回true，否则返回false
     */
    public function verify($userName, $password) {
        $dbConn = new DbConn();
        $conn = $dbConn->getConnection();

        $sql = "SELECT userName, password FROM user WHERE userName = '$userName' AND password = password('$password');";

        $result = $conn->query($sql);

        if (count($result) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public function getUserData($userName)
    {
        $connection = new DbConn();
        $conn = $connection->getConnection();

        $sql = "SELECT userName, password FROM user WHERE userName = '$userName';";

        $result = $conn->query($sql);
        $row = $result->fetch_row();

        if ($row == null) { // 用户名不存在
            return null;
        }

        return new User($row[0], $row[1]);
    }
}