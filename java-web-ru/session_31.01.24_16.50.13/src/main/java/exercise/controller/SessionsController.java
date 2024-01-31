package exercise.controller;

import exercise.dto.LoginPage;
import exercise.dto.MainPage;
import exercise.repository.UsersRepository;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static exercise.util.Security.encrypt;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        String name = ctx.formParam("name");

        var page = new LoginPage(name, "");
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }

    public static void index(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) {
        String name = ctx.formParam("name");
        String password = ctx.formParam("password");

        // Retrieve user from the repository
        var user = UsersRepository.findByName(name);

        if (user != null && Objects.equals(encrypt(password), user.getPassword())) {
            ctx.sessionAttribute("currentUser", name);
            // Authentication successful
            ctx.redirect(NamedRoutes.rootPath());
        } else {
            // Wrong username or password
            var page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", Collections.singletonMap("page", page));
        }
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
