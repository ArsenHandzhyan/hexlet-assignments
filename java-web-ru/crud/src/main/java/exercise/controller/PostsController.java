package exercise.controller;

import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.Collections;
import java.util.List;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
        int postsPerPage = 5;
        Integer currentPage = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);

        List<Post> allPosts = PostRepository.getEntities();
        int totalPosts = allPosts.size();

        int startIndex = (currentPage - 1) * postsPerPage;
        int endIndex = Math.min(startIndex + postsPerPage, totalPosts);

        List<Post> postsOnPage = allPosts.subList(startIndex, endIndex);

        var page = new PostsPage(postsOnPage, currentPage, calculateTotalPages(totalPosts, postsPerPage),
                currentPage > 1, endIndex < totalPosts);

        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }

    private static int calculateTotalPages(int totalItems, int itemsPerPage) {
        return (int) Math.ceil((double) totalItems / itemsPerPage);
    }

    public static void show(Context ctx) {
        long postId = ctx.pathParamAsClass("id", Long.class).get();
        Post post = PostRepository.find(postId).orElseThrow(() -> new NotFoundResponse("Page not found"));
        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        ctx.render("posts/build.jte");
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var body = ctx.formParam("body");

        var post = new Post(name, body);
        PostRepository.save(post);
        ctx.redirect(NamedRoutes.postsPath());
    }

    public static void edit(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new PostPage(post);
        ctx.render("post/edit.jte", Collections.singletonMap("page", page));
    }


    public static void update(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();

        var name = ctx.formParam("name");
        var body = ctx.formParam("email");

        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        post.setName(name);
        post.setBody(body);
        PostRepository.save(post);
        ctx.redirect(NamedRoutes.postsPath());
    }

    //    public static void destroy(Context ctx) {
//        var id = ctx.pathParamAsClass("id", Long.class).get();
//        PostRepository.delete(id);
//        ctx.redirect(NamedRoutes.postsPath());
//    }
    // END
}
