package exercise;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

        public static String getForwardedVariables(String nameConfig) {

        return Stream.of(nameConfig)
                .map(s -> s.trim().replaceAll("^environment=\"|\"$", ""))
                .flatMap(s -> Stream.of(s.split(",")))
                .filter(s -> s.startsWith("X_FORWARDED_"))
                .map(s -> s.substring("X_FORWARDED_".length()))
                .collect(Collectors.joining(","));
    }
}
