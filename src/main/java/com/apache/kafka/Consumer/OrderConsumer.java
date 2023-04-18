package com.apache.kafka.Consumer;

import com.apache.kafka.CustomSerializer.Order;
import com.apache.kafka.producer.OrderCallback;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class OrderConsumer {
    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("I am a Kafka Consumer");

        String bootstrapServers = "127.0.0.1:9092";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.apache.kafka.CustomDeserializer.OrderDeSerializer");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "OrderGroup");

        // create the producer
        KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singletonList("demo_java"));

        // create a producer record
        ConsumerRecords<String, Order> orders =
                consumer.poll(Duration.ofSeconds(20));

        for (ConsumerRecord<String, Order> order : orders) {

            Order orderVal = order.value();
            System.out.println("Customer Name: " + orderVal.getCustomerName());
            System.out.println("Product: " + orderVal.getProduct());
            System.out.println("Quantity: " + orderVal.getQuantity());

        }

        consumer.close();
    }
}
