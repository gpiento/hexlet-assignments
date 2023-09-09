package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App {

    public static List<String> buildApartmentsList(List<Home> apartments, int count) {
        return apartments.stream()
                .sorted(Comparator.comparing(Home::getArea))
                .limit(count)
                .map(Home::toString)
                .toList();
    }

    public static void main(String[] args) {
        List<Home> apartments = new ArrayList<>(List.of(
                new Flat(41, 3, 10),
                new Cottage(125.5, 2),
                new Flat(80, 10, 2),
                new Cottage(150, 3)
        ));

        List<String> result = App.buildApartmentsList(apartments, 3);
        System.out.println(result); // =>

        CharSequence www = new ReversedSequence("abcdef");
        System.out.println(www);

        CharSequence text = new ReversedSequence("abcdef");
        System.out.println(text); // "fedcba"
        text.charAt(1); // 'e'
        text.length(); // 6
        text.subSequence(1, 4); // "edc"
    }
}
