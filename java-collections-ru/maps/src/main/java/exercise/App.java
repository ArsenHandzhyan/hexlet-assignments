package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        String[] words = sentence.split(" ");
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            String lowercaseWord = word.toLowerCase().trim();

            if (!lowercaseWord.isEmpty()) {
                wordCount.put(lowercaseWord, wordCount.getOrDefault(lowercaseWord, 0) + 1);
            }
        }
        return wordCount;
    }

    public static String toString(Map<String, Integer> wordCount) {
        StringBuilder result = new StringBuilder();
        if (wordCount.isEmpty()){
            return "{}";
        }
        result.append("{");

        for (String word : wordCount.keySet()) {
            result.append("\n  ").append(word).append(": ").append(wordCount.get(word));
        }

        result.append("\n}");
        System.out.println(result.toString());
        return result.toString();
    }
}
//END
