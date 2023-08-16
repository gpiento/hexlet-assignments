package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

public class App {

    public static void main(String[] args) {

    }

    public static List<Map<String, String>> findWhere(List<Map<String, String>> listBooks,
                                   Map<String, String> itemFind) {
        List<Map<String, String>> findingBooks = new ArrayList<>();
        for (Map<String, String> i : listBooks) {
            if (i.keySet().containsAll(itemFind.keySet())) {
                if (isFindFields(i, itemFind)) {
                    findingBooks.add(i);
                }
            }
        }
        return findingBooks;
    }

    public static boolean isFindFields(Map<String, String> mapList, Map<String, String> mapFind) {
        for (Map.Entry<String, String> map : mapFind.entrySet()) {
            if (!map.getValue().equals(mapList.get(map.getKey()))) {
                return false;
            }
        }
        return true;
    }
}