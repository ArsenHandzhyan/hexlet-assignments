package exercise;

import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import exercise.model.Article;
import exercise.repository.ArticleRepository;
import io.javalin.Javalin;
import io.javalin.validation.ValidationException;

import java.util.Collections;
import java.util.List;


public final class App {

    public static Javalin getApp() {
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", Collections.singletonMap("page", page));
        });

        app.post("/articles", ctx -> {
            var content = ctx.formParam("content");
            var title = ctx.formParam("title");
            try {
                content = ctx.formParamAsClass("content", String.class)
                        .check(value -> value.length() > 10, "Статья должна быть не короче 10 символов")
                        .get();
                title = ctx.formParamAsClass("title", String.class)
                        .check(value -> value.length() > 2, "Название не должно быть короче двух символов")
                        .check(value -> !ArticleRepository.existsByTitle(value), "Статья с таким названием уже существует")
                        .get();
                var article = new Article(title, content);
                ArticleRepository.save(article);
                ctx.redirect("/articles");
            } catch (ValidationException e) {
                ctx.status(422);
                var page = new BuildArticlePage(title, content, e.getErrors());
                ctx.render("articles/build.jte", Collections.singletonMap("page", page));
            }
        });

        app.get("/articles/build", ctx -> {
            var page = new BuildArticlePage();
            ctx.render("articles/build.jte", Collections.singletonMap("page", page));
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
