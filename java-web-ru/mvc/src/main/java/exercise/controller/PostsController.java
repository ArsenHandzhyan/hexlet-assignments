package exercise.controller;

import exercise.dto.posts.BuildPostPage;
import exercise.dto.posts.EditPostPage;
import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

import java.util.Collections;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, "Название не должно быть короче двух символов")
                    .get();

            var body = ctx.formParamAsClass("body", String.class)
                    .check(value -> value.length() >= 10, "Пост должен быть не короче 10 символов")
                    .get();

            var post = new Post(name, body);
            PostRepository.save(post);
            ctx.redirect(NamedRoutes.postsPath());

        } catch (ValidationException e) {
            var name = ctx.formParam("name");
            var body = ctx.formParam("body");
            var page = new BuildPostPage(name, body, e.getErrors());
            ctx.render("posts/build.jte", Collections.singletonMap("page", page)).status(422);
        }
    }

    public static void index(Context ctx) {
        var posts = PostRepository.getEntities();
        var postPage = new PostsPage(posts);
        ctx.render("posts/index.jte", Collections.singletonMap("page", postPage));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }

    // BEGIN

    public static void edit(Context ctx) {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        var existingPost = PostRepository.find(id);
        if (existingPost.isPresent()) {
            var page = new EditPostPage();
            page.setId(id);
            ctx.render("posts/edit.jte", Collections.singletonMap("page", page));
        } else {
            throw new NotFoundResponse("Post not found");
        }
    }

    public static void update(Context ctx) {
        try {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, "Название не должно быть короче двух символов")
                    .get();

            var body = ctx.formParamAsClass("body", String.class)
                    .check(value -> value.length() >= 10, "Пост должен быть не короче 10 символов")
                    .get();

            var existingPost = PostRepository.find(id);
            if (existingPost.isPresent()) {
                existingPost.get().setName(name);
                existingPost.get().setBody(body);
                ctx.redirect(NamedRoutes.postsPath());
            } else {
                throw new NotFoundResponse("Post not found");
            }
        } catch (ValidationException e) {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var name = ctx.formParam("name");
            var body = ctx.formParam("body");
            var page = new EditPostPage(id, name, body, e.getErrors());
            ctx.render("posts/edit.jte", Collections.singletonMap("page", page)).status(422);
        }
    }
    // END
}
