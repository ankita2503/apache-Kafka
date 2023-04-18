package com.apache.kafka.producer;

import com.apache.kafka.CustomSerializer.Order;
import com.apache.kafka.util.Geolocation;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class OrderProducer {
    private static final Logger log = LoggerFactory.getLogger(OrderProducer.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("I am a Kafka Producer");
        String bootstrapServers = "127.0.0.1:9092";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.apache.kafka.CustomSerializer.OrderSerializer");

        // create the producer
        KafkaProducer<String, Order> producer = new KafkaProducer<>(properties);

        Order order = new Order("Ankita", "product", 11);

        // create a producer record
        ProducerRecord<String, Order> producerRecord =
                new ProducerRecord<>("demo_java", order);

        // send data - asynchronous
        producer.send(producerRecord, new OrderCallback());
        System.out.println("Message Sent Successfully");

//        RecordMetadata recordMetadata = producer.send(producerRecord, new OrderCallback()).get();
//        System.out.println("Message Sent Successfully with recordMetadata : " + recordMetadata.topic());

        // flush data - synchronous
        producer.flush();
        // flush and close producer
        producer.close();
    }
}
