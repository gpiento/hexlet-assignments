package exercise;

import java.util.*;

public class App {

    public static void main(String[] args) {
        System.out.println(scrabble("jvayu", "java"));
    }

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

        boolean allZero = true;

        for (Integer value : charCountMap.values()) {
            if (value > 0) {
                allZero = false;
                break;
            }
        }

        return allZero;
    }
}