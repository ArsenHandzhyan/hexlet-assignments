package exercise;

import exercise.dto.users.UsersPage;
import exercise.model.User;
import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> config.plugins.enableDevLogging());

        // BEGIN
        app.get("/users", ctx -> {
            var term = ctx.queryParam("term");

            List<User> filteredUsers;

            if (term != null) {
                filteredUsers = USERS.stream()
                        .filter(user -> user.getFirstName().toLowerCase().startsWith(term.toLowerCase()))
                        .collect(Collectors.toList());
            } else {
                filteredUsers = USERS;
            }

            var page = new UsersPage(filteredUsers, term);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });
        // END

        app.get("/", ctx -> ctx.render("index.jte"));

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
