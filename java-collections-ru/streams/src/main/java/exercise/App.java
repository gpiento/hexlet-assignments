package exercise;

import java.util.List;
import java.util.Arrays;

public class App {

    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                .filter(string -> (string.endsWith("gmail.com")
                        || string.endsWith("yandex.ru")
                        || string.endsWith("hotmail.com")))
                .count();
    }
}