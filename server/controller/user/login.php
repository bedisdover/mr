<?php
/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-9-17
 * Time: 下午8:56
 *
 * 登录
 */

$userName = $_POST['userName'];
$password = $_POST['password'];

$userDao = UserDao::getInstance();

$message = $userDao->logIn($userName, $password);

echo json_encode($message);