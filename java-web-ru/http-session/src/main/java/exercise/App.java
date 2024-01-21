package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            var per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);

            // Вычисляем индексы начала и конца для текущей страницы и количества элементов на странице
            int startIndex = (page - 1) * per;
            int endIndex = Math.min(startIndex + per, USERS.size());

            // Получаем подсписок
            var resultUsers = USERS.subList(startIndex, endIndex);

            // Отправляем подсписок в формате JSON
            ctx.json(resultUsers);
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
