package exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// BEGIN
public class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        int numRows = image.length;
        int numCols = image[0].length;

        String[][] enlargedImage = new String[numRows * 2][numCols * 2];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                String pixel = image[i][j];
                enlargedImage[i * 2][j * 2] = pixel;
                enlargedImage[i * 2][j * 2 + 1] = pixel;
                enlargedImage[i * 2 + 1][j * 2] = pixel;
                enlargedImage[i * 2 + 1][j * 2 + 1] = pixel;
            }
        }

        return enlargedImage;
    }
}
// END
