package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.Value;

import java.io.IOException;

@Value
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    @SneakyThrows
    public String serialize() {
        return new ObjectMapper().writeValueAsString(this);
    }

    @SneakyThrows
    public static Car unserialize(final String json)  {
        return new ObjectMapper().readValue(json, Car.class);
    }
}

