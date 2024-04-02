package exercise;

import exercise.dto.users.UsersPage;
import exercise.model.User;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import java.util.List;
import java.util.function.Predicate;

import static io.javalin.rendering.template.TemplateUtil.model;
import static org.apache.commons.lang3.StringUtils.startsWithIgnoreCase;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/users", ctx -> {
            String term = ctx.queryParam("term");
            UsersPage page;
            if (term != null) {
                Predicate<User> startsWith = user -> startsWithIgnoreCase(user.getFirstName(), term);
                List<User> userList = USERS.stream()
                        .filter(startsWith)
                        .toList();
                page = new UsersPage(userList);
            } else {
                page = new UsersPage(USERS);
            }
            ctx.render("users/index.jte", model("page", page));

        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(final String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
