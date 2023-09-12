package exercise;

import java.util.Map;

import static exercise.Utils.writeFile;

public class FileKV implements KeyValueStorage {

    private final String path;

    public FileKV(String pathFile, Map<String, String> stringMap) {
        path = pathFile;
        String data = Utils.serialize(stringMap);
        writeFile(path, data);
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> map = toMap();
        map.put(key, value);
        String data = Utils.serialize(map);
        writeFile(path, data);
    }

    @Override
    public void unset(String key) {
        Map<String, String> map = toMap();
        map.remove(key);
        String data = Utils.serialize(map);
        writeFile(path, data);
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> map = toMap();
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        String json = Utils.readFile(path);
        return Utils.unserialize(json);
    }
}
