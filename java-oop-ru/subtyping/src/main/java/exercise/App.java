package exercise;

import java.util.Map;

public class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        for (Map.Entry<String, String> entry : storage.toMap().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            storage.unset(key);
            storage.set(value, key);
        }
    }

    public static void main(String[] args) {
        KeyValueStorage storage = new InMemoryKV(Map.of("key", "10"));
        // Получение значения по ключу
        System.out.println(storage.get("key", "default")); // "10"
        System.out.println(storage.get("unknown", "default")); // "default"
        // Установка нового значения
        storage.set("key2", "value2");
        System.out.println(storage.get("key2", "default")); // "value2"
        // Удаление ключа
        storage.unset("key2");
        System.out.println(storage.get("key2", "default")); // "default"
        // Получение всех данных из базы
        Map<String, String> data = storage.toMap();
        System.out.println(data); // => {key=10};
    }
}
