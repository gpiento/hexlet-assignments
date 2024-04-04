package exercise;

import exercise.dto.users.UsersPage;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.util.Security;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;
import static org.apache.commons.lang3.StringUtils.*;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });

        app.post("/users", ctx -> {
            String firstName = upperCase(ctx.formParam("firstName"));
            String lastName = upperCase(ctx.formParam("lastName"));
            String email = lowerCase(trim(ctx.formParam("email")));
            String password = ctx.formParam("password");
            String passwordConfirmation = ctx.formParam("passwordConfirmation");
            if (!password.equals(passwordConfirmation)) {
                ctx.sessionAttribute("error", "Пароли не совпадают");
                ctx.redirect("/users/build");
                return;
            }
            String cryptPassword = Security.encrypt(password);
            User user = new User(firstName, lastName, email, cryptPassword);
            UserRepository.save(user);
            ctx.redirect("/users");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
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
