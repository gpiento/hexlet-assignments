package exercise;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import exercise.controller.ProductsController;
import exercise.controller.RootController;
import exercise.repository.BaseRepository;
import exercise.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.stream.Collectors;


@Slf4j
public final class App {

    public static Javalin getApp() throws IOException, SQLException {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:hexlet;DB_CLOSE_DELAY=-1;");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        InputStream url = App.class.getClassLoader().getResourceAsStream("schema.sql");
        String sql = new BufferedReader(new InputStreamReader(url))
                .lines().collect(Collectors.joining("\n"));

        log.info(sql);
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get(NamedRoutes.rootPath(), RootController::index);
        app.get(NamedRoutes.buildProductPath(), ProductsController::build);
        app.post(NamedRoutes.productsPath(), ProductsController::create);
        app.get(NamedRoutes.productsPath(), ProductsController::index);
        app.get(NamedRoutes.productPath("{id}"), ProductsController::show);

        return app;
    }

    public static void main(String[] args) throws IOException, SQLException {
        Javalin app = getApp();
        String serverHost = System.getenv("SERVER_HOST");
        if (serverHost == null || serverHost.isEmpty()) {
            serverHost = "localhost";
        }
        int serverPort;
        String serverPortStr = System.getenv("SERVER_PORT");
        if (serverPortStr == null || serverPortStr.isEmpty()) {
            serverPort = 7070;
        } else {
            serverPort = Integer.parseInt(serverPortStr);
            if (serverPort <= 1000 || serverPort > 65535) {
                serverPort = 7070;
            }
        }
        app.start(serverHost, serverPort);
    }
}
