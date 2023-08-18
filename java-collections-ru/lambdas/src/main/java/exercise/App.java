package exercise;

import java.util.Arrays;

public class App {

    public static String[][] enlargeArrayImage(String[][] inputImage) {
        String[][] outputImage = new String[inputImage.length * 2][inputImage[0].length * 2];
        for (int i = 0; i < inputImage.length; i++) {
            for (int j = 0; j < inputImage[0].length; j++) {
                outputImage[i * 2][j * 2] = inputImage[i][j];
                outputImage[i * 2][j * 2 + 1] = inputImage[i][j];
                outputImage[i * 2 + 1][j * 2] = inputImage[i][j];
                outputImage[i * 2 + 1][j * 2 + 1] = inputImage[i][j];
            }
        }
        return outputImage;
    }
}