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

    @Value("${spring.datasource.url}")
    private String PROP_JPA_DATASOURCE_URL;

    @Value("${DATABASE_URL}")
    private String DATABASE_URL;

    @Bean
    public DataSource getDataSource() throws URISyntaxException {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();

        if (DATABASE_URL != null ||
                (PROP_JPA_DATASOURCE_URL == null || PROP_JPA_DATASOURCE_URL.isEmpty())) {
            builder.url(getSpringDataSourceURL(DATABASE_URL));
        } else {
            builder.url(PROP_JPA_DATASOURCE_URL);
        }

        return builder.build();
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
