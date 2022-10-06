package org.stream.app;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Printed;

import java.util.Properties;

public class OrderStreamPipeline {

    public Properties config() {
        Properties props = new Properties();
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "order-streams-app");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        return props;
    }

    public KafkaStreams pipeline() {
        StreamsBuilder builder = new StreamsBuilder();
        Serde<Order> orderSerde = Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(Order.class));
        builder.stream("orders-topic", Consumed.with(Serdes.String(), orderSerde))
                .mapValues(v -> new Order(v, v.getAmount() * v.getQuantity()))
                .print(Printed.toSysOut());
        Topology pipeline = builder.build(config());
        System.out.println("Pipeline topology:");
        System.out.println(pipeline.describe());
        return new KafkaStreams(pipeline, config());
    }

    public static void main(String[] args) throws InterruptedException {
        OrderStreamPipeline example = new OrderStreamPipeline();
        KafkaStreams streams = example.pipeline();
        // pipeline started
        streams.start();
        System.out.println("Producing dummy orders");
        DummyDataGen.produce(10);
        System.out.println("Producing dummy orders completed");
        Thread.sleep(20000);
        // Stopping pipeline
        streams.close();
    }
}
