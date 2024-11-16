package org.example;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Producer{
    private final static String QUEUE_NAME = "email_queue";

    public static String valueToBeSent(String[] args) {
        String word = "Email"; //Default message, requires 1 second of sleep
        int numDots = 0;
        if (args.length > 0) {
            try {
                numDots = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                return null;
            }
        }
        StringBuilder result = new StringBuilder(word);
        for (int i = 0; i < numDots; i++) {
            result.append(".");
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception {
        String message = valueToBeSent(args);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection();
        Channel channel = connection.createChannel()){
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            assert message != null;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}