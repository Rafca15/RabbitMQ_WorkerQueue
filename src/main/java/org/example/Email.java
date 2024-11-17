package org.example;

import java.io.Serializable;

public class Email implements Serializable {
    private String destination;
    private String source;
    private String subject;
    private String body;

    public Email(String destination, String source, String subject, String body) {
        this.destination = destination;
        this.source = source;
        this.subject = subject;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Email{" +
                "destination='" + destination + '\'' +
                ", source='" + source + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
