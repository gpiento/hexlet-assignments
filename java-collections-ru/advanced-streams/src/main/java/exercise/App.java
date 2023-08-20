package exercise;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
//        String content = "environment=\"X_FORWARDED_MAIL=tirion@google.com,X_FORWARDED_HOME=/home/tirion,language=en\"";
        String content = "[program:options]\nenvironment=\"X_FORWARDED_variable=value,  \"\n\nkey=value";
        String result = getForwardedVariables(content);
        System.out.println(result); // => "MAIL=tirion@google.com,HOME=/home/tirion,var3=value"
    }

    public static String getForwardedVariables(String nameConfig) {

        String[] linesArray = nameConfig.split("\n");
        return Stream.of(linesArray)
                .map(s -> s.trim().replaceAll("^environment=\"|\"$", ""))
                .flatMap(s -> Stream.of(s.split(",")))
                .filter(s -> s.startsWith("X_FORWARDED_"))
                .map(s -> s.substring("X_FORWARDED_".length()))
                .collect(Collectors.joining(","));
    }
}
