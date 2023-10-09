package exercise;

import java.util.*;

// BEGIN
public class App {
    public static LinkedHashMap<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        // Обходим ключи из обоих словарей и сравниваем их значения
        for (String key : data1.keySet()) {
            if (data2.containsKey(key)) {
                if (data1.get(key).equals(data2.get(key))) {
                    result.put(key, "unchanged");
                } else {
                    result.put(key, "changed");
                }
            } else {
                result.put(key, "deleted");
            }
        }

        for (String key : data2.keySet()) {
            if (!data1.containsKey(key)) {
                result.put(key, "added");
            }
        }
        return result;
    }
}
//END
