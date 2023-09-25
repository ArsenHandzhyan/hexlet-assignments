package exercise;

import java.util.Arrays;

// BEGIN
public class App {

    public static boolean scrabble(String tiles, String word) {
        if (word.length() > tiles.length()) {
            return false;
        }
        char[] tilesArray = tiles.toLowerCase().toCharArray();
        char[] wordArray = word.toLowerCase().toCharArray();
        Arrays.sort(tilesArray);
        Arrays.sort(wordArray);
        int i = 0;
        for (char letter : wordArray) {
            while (i < tilesArray.length && tilesArray[i] < letter) {
                i++;
            }
            if (i == tilesArray.length || tilesArray[i] > letter) {
                return false;
            }
            i++;
        }
        return true;
    }
}
//END
