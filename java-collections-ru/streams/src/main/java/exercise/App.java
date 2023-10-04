package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
public class App {
    public static void main(String[] args) {
        String[] emails = {
                "info@gmail.com",
                "info@yandex.ru",
                "mk@host.com",
                "support@hexlet.io",
                "info@hotmail.com",
                "support.yandex.ru@host.com"
        };

        List<String> emailsList = List.of(emails);
        long count = getCountOfFreeEmails(emailsList);
        System.out.println(count); // Выведет: 3
    }

    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                .filter(email -> isFreeEmail(email))
                .count();
    }

    private static boolean isFreeEmail(String email) {
        String[] freeDomains = {"gmail.com", "yandex.ru", "hotmail.com"};
        for (String domain : freeDomains) {
            if (email.endsWith("@" + domain)) {
                return true;
            }
        }
        return false;
    }
}
// END
