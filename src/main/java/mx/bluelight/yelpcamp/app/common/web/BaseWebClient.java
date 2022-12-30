package mx.bluelight.yelpcamp.app.common.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class BaseWebClient {

    private static final String LANGUAGE = "es-ES";

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept-Language", LANGUAGE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
