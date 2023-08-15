package exercise;

import java.util.*;

public class App {

    public static void main(String[] args) {
        String sentence = "word text cat apple word map apple word";
        Map<String, Integer> wordsCount = getWordCount(sentence);
        System.out.println(wordsCount); // => {the=1, java=2, is=1, best=1, language=1, programming=1}
    }

    public static Map<String, Integer> getWordCount(String string) {
        if (string.isEmpty()) {
            return new HashMap<>();
        }

        String[] array = string.split(" ");
        Map<String, Integer> map = new HashMap<>();

        for (String s : array) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
//        System.out.println(toString(map));
        return map;
    }

    public static String toString(Map<String, Integer> map) {
        if (map.isEmpty()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(": ")
                    .append(entry.getValue()).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}