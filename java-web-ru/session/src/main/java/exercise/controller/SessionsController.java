package exercise.controller;

import exercise.dto.LoginPage;
import exercise.dto.MainPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

import java.util.Collections;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        MainPage page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        LoginPage page = new LoginPage(null, null);
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }

    public static void login(Context ctx) {
        String nickname = ctx.formParamAsClass("name", String.class).get().trim();
        try {
            String name = ctx.formParamAsClass("name", String.class)
                    .check(UsersRepository::existsByName, "Wrong username or password.")
                    .get().trim();
            User user = UsersRepository.findByName(name);
            String password = ctx.formParamAsClass("password", String.class)
                    .check(p -> Security.encrypt(p).equals(user.getPassword()), "Wrong username or password.")
                    .get();
            ctx.sessionAttribute("currentUser", nickname);
            ctx.redirect(NamedRoutes.rootPath());
        } catch (ValidationException e) {
            LoginPage page = new LoginPage(nickname, e.toString());
            ctx.render("build.jte", Collections.singletonMap("page", page)).status(302);
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
