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

import static io.javalin.rendering.template.TemplateUtil.model;

public class PostsController {

    private static final String ERROR_NAME = "Название не должно быть короче двух символов";
    private static final String ERROR_BODY = "Пост должен быть не короче 10 символов";

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, ERROR_NAME)
                    .get();

            var body = ctx.formParamAsClass("body", String.class)
                    .check(value -> value.length() >= 10, ERROR_BODY)
                    .get();

            var post = new Post(name, body);
            PostRepository.save(post);
            ctx.redirect(NamedRoutes.postsPath());

        } catch (ValidationException e) {
            var name = ctx.formParam("name");
            var body = ctx.formParam("body");
            var page = new BuildPostPage(name, body, e.getErrors());
            ctx.render("posts/build.jte", model("page", page)).status(422);
        }
    }

    public static void index(Context ctx) {
        var posts = PostRepository.getEntities();
        var postPage = new PostsPage(posts);
        ctx.render("posts/index.jte", model("page", postPage));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }

    // BEGIN
    public static void edit(Context ctx) {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        Post post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));
        EditPostPage page = new EditPostPage(id, post.getName(), post.getBody(), null);
        ctx.render("posts/edit.jte", Collections.singletonMap("page", page));
    }

    public static void update(Context ctx) {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        Post post = PostRepository.find(id).orElseThrow(() -> new NotFoundResponse("Post not found"));
        try {
            String name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, ERROR_NAME)
                    .get();

            String body = ctx.formParamAsClass("body", String.class)
                    .check(value -> value.length() >= 10, ERROR_BODY)
                    .get();
            post.setName(name);
            post.setBody(body);
            PostRepository.save(post);
            ctx.redirect(NamedRoutes.postsPath());

        } catch (ValidationException e) {
            String name = ctx.formParam("name");
            String body = ctx.formParam("body");
            EditPostPage page = new EditPostPage(id, name, body, e.getErrors());
            ctx.render("posts/edit.jte", Collections.singletonMap("page", page)).status(422);
        }
    }
    // END
}
