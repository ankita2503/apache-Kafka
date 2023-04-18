package com.apache.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class OrderCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        System.out.println("Inside OnCompletion");
        System.out.println(recordMetadata.partition());
        System.out.println(recordMetadata.offset());
        System.out.println("Message Sent Successfully");
    }
}
