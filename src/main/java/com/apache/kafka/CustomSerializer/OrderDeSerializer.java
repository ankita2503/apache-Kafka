package com.apache.kafka.CustomSerializer;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;

public class OrderDeSerializer implements Deserializer<Order> {

    @Override
    public Order deserialize(String s, byte[] data) {
        Order response = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            response = objectMapper.readValue(data,Order.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }


}
