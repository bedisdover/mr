<?php

/**
 * Created by PhpStorm.
 * User: song
 * Date: 16-9-17
 * Time: 下午9:07
 */
class UserDao
{
    private static $_instance = null;

    private function __construct()
    {
    }

    /**
     * 登录
     * @param $userName
     * 用户名
     * @param $password
     * 密码
     * @return Message
     * 返回信息,分为以下三种情况
     * {0, "用户名不存在", null}
     * {0, "密码错误", null}
     * {1, "登录成功", user}, user为用户对象
     */
    public function logIn($userName, $password)
    {
        $userData = new UserData();
        $user = $userData->getUserData($userName);

        if ($user === null) {
            return new Message(0, "用户名不存在", null);
        }

        if ($userData->verify($userName, $password)) {
            return new Message(1, "登录成功", $user);
        } else {
            return new Message(0, "密码错误", null);
        }
    }

    /**
     * 注册用户
     * @param $userName
     * 用户名
     * @param $password
     * 密码
     * @return Message
     * 返回信息,分为以下三种情况
     * {0, "用户名已存在", null}
     * {0, "用户注册失败", null}, 可能由数据库连接错误导致
     * {1, "用户注册成功", null}
     */
    public function signUp($userName, $password)
    {
        $userData = new UserData();

        $info = $userData->addNewUser($userName, $password);

        $state = $info === "用户注册成功" ? 1 : 0;

        return new Message($state, $info, null);
    }

    /**
     * @return UserDao
     */
    public static function getInstance()
    {
        if (is_null(self::$_instance)) {
            self::$_instance = new UserDao();
        }

        return self::$_instance;
    }
}