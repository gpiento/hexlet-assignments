package exercise.controller;

import exercise.dto.posts.PostPage;
import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import io.javalin.http.Context;

import java.util.List;
import java.util.Optional;

import static io.javalin.rendering.template.TemplateUtil.model;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
        List<Post> allPosts = PostRepository.getEntities();
        Optional<String> pageParam = Optional.ofNullable(ctx.queryParam("page"));
        long page;
        PostsPage postsPage;
        if (pageParam.isPresent()) {
            if (Long.parseLong(pageParam.get()) <= 0) {
                page = 1L;
            } else {
                page = Long.parseLong(pageParam.get());
            }
            postsPage = new PostsPage(allPosts.stream()
                    .skip((page - 1) * 5)
                    .limit(5)
                    .toList(), page);
        } else {
            postsPage = new PostsPage(allPosts.stream().limit(5).toList(), 1L);
        }
        ctx.render("posts/index.jte", model("postsPage", postsPage));
    }

    public static void show(Context ctx) {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        Optional<Post> optionalPost = PostRepository.find(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            PostPage postPage = new PostPage(post);
            ctx.render("posts/show.jte", model("postPage", postPage));
        } else {
            ctx.status(404);
            ctx.result("Page not found");
        }
    }


    // END
}
