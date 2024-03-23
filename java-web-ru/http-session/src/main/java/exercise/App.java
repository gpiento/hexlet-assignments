package exercise;

import io.javalin.Javalin;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        app.get("/users", ctx -> {
            Optional<String> page = Optional.ofNullable(ctx.queryParam("page"));
            Optional<String> per = Optional.ofNullable(ctx.queryParam("per"));
            if (page.isPresent() && per.isPresent()) {
                ctx.json(USERS.stream()
                        .skip((Long.parseLong(page.get()) - 1) * Long.parseLong(per.get()))
                        .limit(Integer.parseInt(per.get()))
                        .toList());
            } else {
                ctx.json(USERS.stream().limit(5).toList());
            }
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
