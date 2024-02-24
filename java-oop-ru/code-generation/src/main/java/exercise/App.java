package exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

class App {

    public static void main(String[] args) {
        User owner = new User(1, "Ivan", "P", 25);
        System.out.println(owner.getFirstName()); // "Ivan"

        Car car = new Car(1, "bmv", "x5", "black", owner);
        String jsonRepresentation = car.serialize();
        System.out.println(jsonRepresentation); // =>
  /*
  {
    "id":1,
    "brand":"bmv",
    "model":"x5",
    "color":"black",
    "owner":{
        "id":1,
        "firstName":"Ivan",
        "lastName":"P",
        "age":25
    }
  }
  */

        //String jsonRepresentation = ;
        Car instance = Car.unserialize(jsonRepresentation);
        System.out.println(instance.getBrand()); // "bmv"
        System.out.println(instance.getModel()); // "x5"

        Path path1 = Paths.get("/tmp/file1.json");
        Car car1 = new Car(1, "audi", "q3", "black", owner);
        App.save(path1, car1); // Сохраняет представление объекта в файл

        Path path2 = Paths.get("/tmp/file2.json");
        Car car2 = App.extract(path2); // Возвращает инстанс класса Car
        System.out.println(car2.getModel()); // "passat"
    }

    public static void save(final Path path, final Car car) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            writer.write(car.serialize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Car extract(final Path path) {
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Car.unserialize(json.toString());
    }
}
