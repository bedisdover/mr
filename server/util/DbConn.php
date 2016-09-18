<?php

/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-9-17
 * Time: 下午9:22
 *
 * 数据库连接
 */
class DbConn
{
    private $serverName = "114.215.88.219";

    private $userName = "admin";

    private $password = "admin";

    private $dbName = "mr";

    private $conn;

    public function __construct()
    {
        $this->conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);
    }

    public function getConnection()
    {
        if ($this->conn->connect_error) {
            return null;
        }
        return $this->conn;
    }
}