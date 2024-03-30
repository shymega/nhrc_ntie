package uk.nhrc.apps.ntie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class ApplicationConfiguration {

    @Value("${DATABASE_URL:#{null}}")
    private String DATABASE_URL;

    @Value("${spring.datasource.url:#{null}}")
    private String SPRING_DATASOURCE_URL;

    private final String DEFAULT_DB_URL = "jdbc:h2:mem:nhrc_ntie;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;DEFAULT_NULL_ORDERING=HIGH";

    @Bean
    public DataSource getDataSource() throws URISyntaxException {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();

        if (DATABASE_URL != null) {
            return builder.url(getSpringDataSourceURL(DATABASE_URL))
                .build();
        } else if (SPRING_DATASOURCE_URL != null) {
            return builder.url(SPRING_DATASOURCE_URL)
                .build();
        } else {
            return builder.url(DEFAULT_DB_URL)
                .build();
        }
    }

    private String getSpringDataSourceURL(final String databaseURL) throws URISyntaxException {
        final URI uri = new URI(databaseURL);
        String username = "";
        String password = "";
        final String userInfo = uri.getUserInfo();
        if (userInfo != null) {
            final String[] components = userInfo.split(":");
            username = components[0];
            if (components.length == 2) {
                password = components[1];
            }
        }
        final String host = uri.getHost();
        int port = uri.getPort();
        final String path = uri.getPath();
        String scheme = uri.getScheme();
        if ("postgres".equals(scheme)) {
            scheme = "postgresql";
        }
        String jdbcURL = "jdbc:" + scheme + "://" + host + ":" + port + path;
        if (!username.isEmpty()) {
            jdbcURL = jdbcURL + "?user=" + username;
            if (!password.isEmpty()) {
                jdbcURL = jdbcURL + "&password=" + password;
            }
        }
        return jdbcURL;
    }
}
