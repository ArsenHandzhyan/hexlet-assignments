package exercise;

import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

@Value
public class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Car unserialize(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, Car.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
