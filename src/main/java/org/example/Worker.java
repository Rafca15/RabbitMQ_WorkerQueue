package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.*;

public class Worker {
    private final static String QUEUE_NAME = "email_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for emails. Press CTRL+C to exit.");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(delivery.getBody());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                Email email = (Email) objectInputStream.readObject();

                System.out.println(" [x] Received Email: " + email);
                objectInputStream.close();
            } catch (Exception e){
                System.out.println("Failed to process email.");
            } finally {
                System.out.println(" [x] Work is done.");
            }

        };

        boolean autoAck = true;
        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {});
    }
}