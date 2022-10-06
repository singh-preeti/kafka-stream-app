package org.stream.app;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.*;

public class DummyDataGen {

    private static final String[] products = {"Computer", "Mobile", "Sports", "Health"};

    private static Properties config() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        return props;
    }

    public static void produce(int number) throws InterruptedException {
        Random random = new Random();
        KafkaProducer<String, Order> producer = new KafkaProducer<>(config());
        for (int i = 0; i < number; i++) {
            Thread.sleep(1000);
            int nextInt = random.nextInt(products.length);
            String id = UUID.randomUUID().toString();
            Order order = new Order(id, products[nextInt], random.nextDouble() * 10, nextInt + 10);
            System.out.println("Sending Dummy Order:" + order);
            producer.send(new ProducerRecord<>("orders-topic", id, order));
        }
        producer.flush();
        producer.close();
    }
}
