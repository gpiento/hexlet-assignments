package exercise;

import java.util.List;
import java.util.Arrays;

public class App {

    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                .filter(App::isFreeEmail)
                .count();
    }

    public static boolean isFreeEmail(String email) {
        return email.endsWith("gmail.com")
                || email.endsWith("yandex.ru")
                || email.endsWith("hotmail.com");
    }
}