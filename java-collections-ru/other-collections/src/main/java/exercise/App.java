package exercise;

import java.util.*;

public class App {

    public static void main(String[] args) {
        Map<String, Object> data1 = new HashMap<>(
                Map.of("one", "eon", "two", "two", "four", true)
        );
        System.out.println(data1); //=> {two=two, four=true, one=eon}

        Map<String, Object> data2 = new HashMap<>(
                Map.of("two", "own", "zero", 4, "four", true)
        );
        System.out.println(data2); //=> {zero=4, two=own, four=true}

        Map<String, String> result = App.genDiff(data1, data2);
        System.out.println(result); //=> {four=unchanged, one=deleted, two=changed, zero=added}
    }

    public static LinkedHashMap<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> commonKeys = new HashSet<>(map1.keySet());
        commonKeys.retainAll(map2.keySet());
        Set<String> uniqueMap1 = new HashSet<>(map1.keySet());
        uniqueMap1.removeAll(commonKeys);
        Set<String> uniqueMap2 = new HashSet<>(map2.keySet());
        uniqueMap2.removeAll(commonKeys);

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();

        for (String s : commonKeys) {
            if (map1.get(s).equals(map2.get(s))) {
                linkedHashMap.put(s, "unchanged");
            } else {
                linkedHashMap.put(s, "changed");
            }
        }

        for (String s : uniqueMap1) {
            linkedHashMap.put(s, "deleted");
        }

        for (String s : uniqueMap2) {
            linkedHashMap.put(s, "added");
        }

        List<String> sortedKeys = new ArrayList<>(linkedHashMap.keySet());
        Collections.sort(sortedKeys);

        LinkedHashMap<String, String> sortedLinkedHashMap = new LinkedHashMap<>();
        for (String key : sortedKeys) {
            sortedLinkedHashMap.put(key, linkedHashMap.get(key));
        }

        return sortedLinkedHashMap;
    }
}