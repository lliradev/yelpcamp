package mx.bluelight.yelpcamp.app.exception;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.dto.CommonResponseError;
import mx.bluelight.yelpcamp.app.dto.StackTrace;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
class RestControllerAdvisor {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<CommonResponseError> httpClientErrorExceptionHandler(HttpClientErrorException ex) {
        CommonResponseError responseError = new CommonResponseError();

        responseError.setMessage(ex.getMessage());
        responseError.setTraces(this.mapperTraces(ex.getStackTrace()));
        responseError.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.status(ex.getStatusCode()).body(responseError);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<CommonResponseError> httpServerErrorExceptionHandler(HttpServerErrorException ex) {
        CommonResponseError responseError = new CommonResponseError();

        responseError.setMessage(ex.getMessage());
        responseError.setTraces(this.mapperTraces(ex.getStackTrace()));
        responseError.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.status(ex.getStatusCode()).body(responseError);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<CommonResponseError> nullPointerExceptionHandler(NullPointerException ex) {
        CommonResponseError responseError = new CommonResponseError();

        responseError.setMessage(ex.getMessage());
        responseError.setTraces(this.mapperTraces(ex.getStackTrace()));
        responseError.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.internalServerError().body(responseError);
    }

    private List<StackTrace> mapperTraces(StackTraceElement[] stackTraceElements) {
        List<StackTrace> traces = new ArrayList<>();
        for (StackTraceElement element : stackTraceElements) {
            StackTrace trace = new StackTrace();
            trace.setClassName(element.getClassName());
            trace.setMethodName(element.getMethodName());
            trace.setLineNumber(element.getLineNumber());
            traces.add(trace);
        }
        return traces;
    }
}
