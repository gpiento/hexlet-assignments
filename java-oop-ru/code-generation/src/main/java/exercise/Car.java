package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;

import java.io.IOException;

@Value
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    public String serialize() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car unserialize(final String json) {
        try {
            return new ObjectMapper().readValue(json, Car.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

