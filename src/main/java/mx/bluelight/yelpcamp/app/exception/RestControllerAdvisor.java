package mx.bluelight.yelpcamp.app.exception;

import mx.bluelight.yelpcamp.app.dto.TranslationResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<TranslationResponseError> httpClientErrorExceptionHandler(HttpClientErrorException ex) {
        TranslationResponseError responseError = new TranslationResponseError();

        responseError.setMessage(ex.getMessage());
        responseError.setStackTraceElements(ex.getStackTrace());
        responseError.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.status(ex.getStatusCode()).body(responseError);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<TranslationResponseError> httpServerErrorExceptionHandler(HttpServerErrorException ex) {
        TranslationResponseError responseError = new TranslationResponseError();

        responseError.setMessage(ex.getMessage());
        responseError.setStackTraceElements(ex.getStackTrace());
        responseError.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.status(ex.getStatusCode()).body(responseError);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<TranslationResponseError> nullPointerExceptionHandler(NullPointerException ex) {
        TranslationResponseError responseError = new TranslationResponseError();

        responseError.setMessage(ex.getMessage());
        responseError.setStackTraceElements(ex.getStackTrace());
        responseError.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.internalServerError().body(responseError);
    }
}
