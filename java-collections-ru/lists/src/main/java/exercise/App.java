package exercise;

import java.util.*;

public class App {

    public static boolean scrabble(String heap, String word) {
        Map<Character, Integer> charCountMap = new HashMap<>();

        for (char c : word.toCharArray()) {
            charCountMap.put(Character.toLowerCase(c), charCountMap.getOrDefault(c, 0) + 1);
        }

        for (Character c : heap.toCharArray()) {
            char charK = Character.toLowerCase(c);
            if (charCountMap.containsKey(charK)) {
                charCountMap.put(charK, charCountMap.get(charK) - 1);
            }
        }

        return charCountMap.values().stream().allMatch(value -> value == 0);
    }
}