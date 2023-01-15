package mx.bluelight.yelpcamp.app.common.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class BaseWebClient {

    private static final String LANGUAGE_ES = "es-ES";

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT_LANGUAGE, LANGUAGE_ES);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
