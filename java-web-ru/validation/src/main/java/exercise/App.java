package exercise;

import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;
import exercise.model.Article;
import exercise.repository.ArticleRepository;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import io.javalin.validation.ValidationException;

import java.util.List;

import static exercise.repository.ArticleRepository.existsByTitle;
import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", model("page", page));
        });

        // BEGIN
        app.get("/articles/build", ctx -> {
            BuildArticlePage page = new BuildArticlePage();
            ctx.render("articles/build.jte", model("page", page));
        });

        app.post("/articles", ctx -> {
            try {
                String title = ctx.formParamAsClass("title", String.class)
                        .check(t -> t.length() >= 2, "Название статьи меньше 2 символов.")
                        .check(t -> !existsByTitle(t), "Статья с таким названием уже существует.")
                        .get().trim();
                String content = ctx.formParamAsClass("content", String.class)
                        .check(c -> c.length() >= 10, "Статья короче 10 символов.")
                        .get().trim();
                Article article = new Article(title, content);
                ArticleRepository.save(article);
                ctx.redirect("/articles");
            } catch (ValidationException e) {
                String title = ctx.formParam("title");
                String content = ctx.formParam("content");
                BuildArticlePage page = new BuildArticlePage(title, content, e.getErrors());
                ctx.render("articles/build.jte", model("page", page)).status(422);
            }
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
