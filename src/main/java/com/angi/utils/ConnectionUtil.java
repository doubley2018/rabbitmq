package com.angi.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author angi
 * @date 2021/1/15 23:27
 */
public class ConnectionUtil {
    public static Connection getConnection() throws Exception {
//        1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
//        2.设置参数
        //主机名称，默认localhost
        connectionFactory.setHost("192.168.19.128");
        //连接端口
        connectionFactory.setPort(5672);
        //虚拟主机名称，默认/
        connectionFactory.setVirtualHost("/vhost");
        //连接用户名，默认guest
        connectionFactory.setUsername("root");
        //连接用户密码，默认guest
        connectionFactory.setPassword("root2020");
        return connectionFactory.newConnection();
    }

}
