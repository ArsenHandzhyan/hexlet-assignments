package exercise;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {

    public static void main(String[] args) throws Exception {
        String DATA1 = readFixture("s1.conf");
        String DATA2 = readFixture("s2.conf");

        System.out.println("data1: " + getForwardedVariables(DATA1));
        System.out.println("data2: " + getForwardedVariables(DATA2));
    }

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    public static String getForwardedVariables(String configFileContent) {
        // Создаем регулярное выражение для поиска переменных окружения в формате "X_FORWARDED_..."
        Pattern pattern = Pattern.compile("X_FORWARDED_([^=]+)=([^,\"]+)");

        // Ищем строки, содержащие "environment="
        return Arrays.stream(configFileContent.split("\n"))
                .filter(line -> line.contains("environment="))
                .flatMap(line -> {
                    Matcher matcher = pattern.matcher(line);
                    List<String> forwardedVariables = new ArrayList<>();
                    while (matcher.find()) {
                        forwardedVariables.add(matcher.group(1) + "=" + matcher.group(2));
                    }
                    return forwardedVariables.stream();
                })
                .collect(Collectors.joining(","));
    }
}
//END
