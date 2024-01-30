package exercise;

import exercise.model.User;
import exercise.repository.UserRepository;
import io.javalin.Javalin;
import exercise.controller.UsersController;
import exercise.util.NamedRoutes;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.post(NamedRoutes.usersPath(), UsersController::create);


        // END

        app.get(NamedRoutes.userPath("{id}"), UsersController::show);
        return app;
    }

    public static void main(String[] args) {
        var user = new User("asd", "asd","as", "as", "asas");
        UserRepository.save(user);
        Javalin app = getApp();
        app.start(7070);
    }
}
