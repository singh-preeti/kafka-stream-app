package org.stream.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class JsonSerializer<T> implements Serializer<T> {

    private final Gson gson;

    public JsonSerializer() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String topic, T t) {
        return gson.toJson(t).getBytes(UTF_8);
    }

    @Override
    public void close() {

    }
}
