package cn.edu.nju.mr.client.dao;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于获取
 * Created by coral on 16-9-3.
 */
public abstract class DaoBase {

    public static String Host = "http://114.215.88.219:8082";

    /**
     * 指定接口。
     * 不一定要实现这个方法。
     *
     * @return api地址
     */
    public abstract String getAction();

    /**
     * 在这里定义完整的接口url。
     *
     * @return 完整的接口url。如果是get请求应该包含所有的参数
     */
    public abstract String getFullUrl();

    /**
     * 在这里发送自定义http请求并返回响应内容。
     *
     * @return 服务器响应内容。
     */
    public abstract String sendRequest();

    /**
     * 发送请求，并将结果封装为Map（如果返回值是json），否则将封装为默认
     * map。
     *
     * @return 返回的json的map，或者包装为默认map的非json数据。
     */
    public Map readData() {
        Map data = null;
        String jsonString = sendRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            data = objectMapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            System.err.println("读取到了数据，但是在解析为json的时候出现了错误。将结果包装为默认对象。");
            data = new HashMap();
            data.put("error", 1);
            data.put("message", "Error parsing Json string");
            data.put("data", jsonString);
        }
        return data;
    }
}