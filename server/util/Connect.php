<?php

/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-9-17
 * Time: 下午9:22
 *
 * 数据库连接
 */
class Connect
{
    private $serverName = "127.0.0.1";

    private $userName = "root";

    private $password = "123456";

    private $dbName = "mr";

    private $conn = null;

    public function getInstance() {
        $this->conn = new mysqli($this->serverName, $this->userName, $this->password, $this->dbName);

        return $this->conn;
    }
}