package exercise;

import java.util.stream.Collectors;
import java.util.Map;

public class Tag {

    String tag;

    public static void main(String[] args) {
        Tag img = new SingleTag("img", Map.of("class", "v-10", "id", "wop"));
        System.out.println(img.toString()); // "<img class="v-10" id="wop">
    }
}
