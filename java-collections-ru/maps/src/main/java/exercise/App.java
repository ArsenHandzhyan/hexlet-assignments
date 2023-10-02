package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        // Разделяем предложение на слова, используя пробел как разделитель
        String[] words = sentence.split(" ");
        Map<String, Integer> wordCount = new HashMap<>();

        // Подсчитываем количество каждого слова
        for (String word : words) {
            // Приводим слова к нижнему регистру, чтобы учесть регистронезависимость
            String lowercaseWord = word.toLowerCase();

            // Если слово уже есть в словаре, увеличиваем счетчик на 1, иначе добавляем новое слово
            wordCount.put(lowercaseWord, wordCount.getOrDefault(lowercaseWord, 0) + 1);
        }

        return wordCount;
    }

    public static String toString(Map<String, Integer> wordCount) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        // Обходим все ключи (слова) в словаре
        for (String word : wordCount.keySet()) {
            result.append("  ").append(word).append(": ").append(wordCount.get(word)).append("\n");
        }

        result.append("}");

        return result.toString();
    }
}
//END
