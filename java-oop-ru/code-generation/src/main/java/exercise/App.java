package exercise;

import java.nio.file.Path;
import java.nio.file.Files;

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
}
