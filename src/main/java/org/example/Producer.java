package org.example;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.*;

public class Producer{
    private final static String QUEUE_NAME = "email_queue";

    public static Email createEmailObject(String[] args) {
        String to = "destination@example.com";
        String from = "source@example.com";
        String subject = "Default Subject";
        String body = "This is the default email body.";

        if (args.length > 0) {
            subject = args[0];
        }
        if (args.length > 1) {
            body = args[1];
        }

        return new Email(to, from, subject, body);
    }

    public static void main(String[] args) throws Exception {
        Email email = createEmailObject(args);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try(Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()){
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(email);
            objectOutputStream.close();

            byte[] emailBytes = byteArrayOutputStream.toByteArray();
            channel.basicPublish("", QUEUE_NAME, null, emailBytes);
            System.out.println(" [x] Sent '" + email + "'");
        }
    }
}