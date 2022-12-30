package mx.bluelight.yelpcamp.app.common.exception;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.common.dto.ResponseBase;
import mx.bluelight.yelpcamp.app.common.dto.ResponseErrorBase;
import mx.bluelight.yelpcamp.app.common.dto.StackTrace;
import mx.bluelight.yelpcamp.app.exception.CustomBusinessException;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ResponseErrorBase> httpClientErrorExceptionHandler(HttpClientErrorException ex) {
        ResponseErrorBase responseError = new ResponseErrorBase();

        responseError.setMessage(ex.getMessage());
        responseError.setTraces(this.mapperTraces(ex.getStackTrace()));
        responseError.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.status(ex.getStatusCode()).body(responseError);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ResponseErrorBase> httpServerErrorExceptionHandler(HttpServerErrorException ex) {
        ResponseErrorBase responseError = new ResponseErrorBase();

        responseError.setMessage(ex.getMessage());
        responseError.setTraces(this.mapperTraces(ex.getStackTrace()));
        responseError.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.status(ex.getStatusCode()).body(responseError);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseErrorBase> nullPointerExceptionHandler(NullPointerException ex) {
        ResponseErrorBase responseError = new ResponseErrorBase();

        responseError.setMessage(ex.getMessage());
        responseError.setTraces(this.mapperTraces(ex.getStackTrace()));
        responseError.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.internalServerError().body(responseError);
    }

    @ExceptionHandler(CustomBusinessException.class)
    public ResponseEntity<ResponseBase<Object>> customBusinessExceptionHandler(CustomBusinessException ex) {
        ResponseBase<Object> response = new ResponseBase<>();

        response.setCode(ex.getCode());
        response.setDescription(ex.getMessage());

        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ResponseErrorBase> numberFormatExceptionHandler(NumberFormatException ex) {
        ResponseErrorBase responseError = new ResponseErrorBase();
        final String detail = "Failed to convert value of type 'String' to required type number.";

        responseError.setMessage(detail.concat(" ").concat(ex.getMessage()));
        responseError.setTraces(this.mapperTraces(ex.getStackTrace()));
        responseError.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.badRequest().body(responseError);
    }

    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<ResponseErrorBase> notImplementedExceptionHandler(NotImplementedException ex) {
        ResponseErrorBase responseError = new ResponseErrorBase();

        responseError.setMessage(ex.getMessage());
        responseError.setTraces(this.mapperTraces(ex.getStackTrace()));
        responseError.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(responseError);
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
