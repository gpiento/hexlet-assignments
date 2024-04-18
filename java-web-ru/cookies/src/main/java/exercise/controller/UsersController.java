package exercise.controller;

import exercise.dto.users.UserPage;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

import java.util.Collections;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) {
        String firstName = ctx.formParamAsClass("firstName", String.class).get();
        String lastName = ctx.formParamAsClass("lastName", String.class).get();
        String email = ctx.formParamAsClass("email", String.class).get();
        String password = ctx.formParamAsClass("password", String.class).get();
        String token = Security.generateToken();
        User user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);
        ctx.redirect(NamedRoutes.userPath(user.getId()));
        ctx.cookie("token", token);
    }

    public static void show(Context ctx) {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        User user = UserRepository.find(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user.getToken().equals(ctx.cookie("token"))) {
            UserPage page = new UserPage(user);
            ctx.render("users/show.jte", Collections.singletonMap("page", page));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
