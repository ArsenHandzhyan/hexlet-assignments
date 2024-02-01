package exercise.controller;

import exercise.dto.posts.BuildPostPage;
import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.Collections;

public class PostsController {

    public static void build(Context ctx) {
        var flash = ctx.consumeSessionAttribute("flash");
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", Collections.singletonMap("page", page));
    }

    // BEGIN
    public static void index(Context ctx) {
        var flash = ctx.consumeSessionAttribute("flash");
        // Добавляем flash в определение CoursesPage
        var posts = PostRepository.getEntities();
        var page = new PostsPage(posts);
        page.setFlash(String.valueOf(flash));
        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var body = ctx.formParamAsClass("body", String.class)
                .check(value -> value.length() > 2, "body должно быть не короче 2 символов")
                .get();

        var post = new Post(name, body);
        PostRepository.save(post);
        ctx.sessionAttribute("flash", "Пост был успешно создан!");
        ctx.redirect(NamedRoutes.postsPath());
    }

    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }
}
