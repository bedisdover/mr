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

    public function logIn($userName, $password)
    {

    }

    public function signIn($userName, $password)
    {

    }

    public function logout()
    {

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