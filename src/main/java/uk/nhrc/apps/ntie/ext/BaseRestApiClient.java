package uk.nhrc.apps.ntie.ext;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.JdkClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpClient;
import java.time.Duration;

public class BaseRestApiClient {
    private final HttpClient httpClient = HttpClient.newBuilder()
        .followRedirects(HttpClient.Redirect.ALWAYS)
        .connectTimeout(Duration.ofSeconds(30))
        .build();

    private final ClientHttpConnector connector = new JdkClientHttpConnector(httpClient);
    protected final WebClient webClient = WebClient.builder()
        .clientConnector(connector)
        .defaultHeader("Accept", "application/json")
        .build();

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected <T> T readJsonToObject(final String inJson, final Class<T> targetClass) {
        try {
            return objectMapper.readValue(inJson, targetClass);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected String writeObjectToJson(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
