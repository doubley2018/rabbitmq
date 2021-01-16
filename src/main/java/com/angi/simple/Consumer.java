package com.angi.simple;

import com.angi.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author angi
 * @date 2021/1/15 23:33
 */
public class Consumer {
    static final String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        final Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //创建消费者，并设置消息处理
        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                int i = 1 / 0;
                System.out.println("消费者唯一标识：" + consumerTag);
                System.out.println("路由key为：" + envelope.getRoutingKey());
                System.out.println("交换机为：" + envelope.getExchange());
                System.out.println("消息唯一id：" + envelope.getDeliveryTag());
                System.out.println("接受到的消息：" + new String(body, "utf-8"));

                //手动ACK，确认字符,第一个参数消息唯一id，第二个参数是否所有消息都确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //监听消息
        /*
         * 参数1：队列名称
         * 参数2：是否自动确认，设置为true表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，
                              设置为false则需要手动确认
         * 参数3：消息接收到后回调
         */

//        channel.basicConsume(QUEUE_NAME, true, consumer);
        channel.basicConsume(QUEUE_NAME, false, consumer);
        //不关闭资源，一直监听消息
//        channel.close();
//        connection.close();
    }

}
