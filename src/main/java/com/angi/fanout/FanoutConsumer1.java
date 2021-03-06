package com.angi.fanout;

import com.angi.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author angi
 * @date 2021/1/16 23:40
 */
public class FanoutConsumer1 {
    private final static String EXCHANGE_NAME = "fanout_exchange";

    private final static String QUEUE_NAME = "fanout_exchange_queue_2";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("消费者1" + msg);

            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
