package exercise;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Objects;


public class App {
    public static void save(Path path, Car c) {
        try {
            String json = c.serialize();
            Files.write(path, json.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Car extract(Path path) {
        try {
            String json = new String(Files.readAllBytes(path));
            return Car.unserialize(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        User owner = new User(1, "Ivan", "P", 25);
// Вызываем автоматически сгенерированный геттер
        owner.getFirstName();
        Car car = new Car(1, "bmv", "x5", "black", owner);
        String jsonRepresentation = car.serialize();
        System.out.println(jsonRepresentation);
        String jsonRepresentation1 = car.serialize();// получаем JSON представление объекта
        Car instance = Car.unserialize(jsonRepresentation1);
        System.out.println(instance.getBrand());
        System.out.println(instance.getModel());
    }
}
