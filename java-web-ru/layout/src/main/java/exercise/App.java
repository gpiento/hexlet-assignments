package exercise;

import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import exercise.model.User;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/users/{id}", ctx -> {
            Long id = ctx.pathParamAsClass("id", Long.class).get();

            User user = USERS.stream()
                    .filter(u -> id.equals(u.getId()))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("User not found"));

            UserPage page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        });

        app.get("/users", ctx -> {
            UsersPage page = new UsersPage(USERS);
            ctx.render("users/index.jte", model("page", page));

        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
