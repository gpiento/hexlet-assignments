package exercise;

import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class Sorter {

    public static List<String> takeOldestMans(List<Map<String, String>> user) {
        List<String> result = new ArrayList<>();
        return user.stream()
                .filter(map -> map.get("gender").equals("male"))
                .sorted((Map<String, String> map1, Map<String, String> map2)
                        -> LocalDate.parse(map1.get("birthday")).compareTo(LocalDate.parse(map2.get("birthday"))))
                .map(map -> map.get("name"))
                .collect(Collectors.toList());
    }
}