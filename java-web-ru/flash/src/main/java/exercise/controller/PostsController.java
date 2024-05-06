package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.BasePage;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    // BEGIN
    public static void create(Context ctx) {
        try {
            String name = ctx.formParamAsClass("name", String.class)
                    .check(n -> n.length() >= 2, "Название не должно быть короче двух символов")
                    .get();
            String body = ctx.formParamAsClass("body", String.class).get();
            Post post = new Post(name, body);
            PostRepository.save(post);
            ctx.sessionAttribute("flash", "Post has been created!");
            ctx.redirect(NamedRoutes.postsPath());

        } catch (ValidationException e) {
            String name = ctx.formParam("name");
            String body = ctx.formParam("body");
            BuildPostPage page = new BuildPostPage(name, body, e.getErrors());
            ctx.sessionAttribute("flash", "Название не должно быть короче двух символов.");
            ctx.render("posts/build.jte", model("page", page));
        }
    }

    public static void index(Context ctx) {
        List<Post> allPosts = PostRepository.getEntities();
        PostsPage postsPage = new PostsPage(allPosts);
        postsPage.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("posts/index.jte", model("postsPage", postsPage));
    }
    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
}
