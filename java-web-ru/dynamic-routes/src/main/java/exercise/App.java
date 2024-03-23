package exercise;

import io.javalin.Javalin;
import io.javalin.validation.Validator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        app.get("/companies/{id}", ctx -> {
            Validator<Integer> id = ctx.pathParamAsClass("id", Integer.class);
            Optional<Map<String, String>> optionalMap = COMPANIES.stream()
                    .filter(c -> c.get("id").equals(Integer.toString(id.get())))
                    .findFirst();
            if (optionalMap.isPresent()) {
                ctx.json(optionalMap.get());
            } else {
                ctx.status(404);
                ctx.result("Company not found");
            }
        });

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
