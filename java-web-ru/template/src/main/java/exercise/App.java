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

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/users/{id}", ctx -> {
            String id = ctx.pathParam("id");
            User user = USERS.stream()
                    .filter(u -> u.getId() == Integer.parseInt(id))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundResponse("User with id = " + id + " not found."));
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
