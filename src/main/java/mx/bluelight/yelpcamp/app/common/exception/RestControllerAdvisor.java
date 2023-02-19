package mx.bluelight.yelpcamp.app.common.exception;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.common.constant.ResponseCode;
import mx.bluelight.yelpcamp.app.common.dto.ResponseBase;
import mx.bluelight.yelpcamp.app.common.dto.ResponseErrorBase;
import mx.bluelight.yelpcamp.app.common.dto.StackTrace;
import mx.bluelight.yelpcamp.app.exception.CustomBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
class RestControllerAdvisor {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ResponseErrorBase> exceptionHandler(HttpClientErrorException ex) {
        ResponseErrorBase responseError = new ResponseErrorBase();
        responseError.setMessage(ex.getMessage());
        responseError.setTraces(this.mapperStackTraces(ex.getStackTrace()));
        return ResponseEntity.status(ex.getStatusCode()).body(responseError);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ResponseErrorBase> exceptionHandler(HttpServerErrorException ex) {
        ResponseErrorBase responseError = new ResponseErrorBase();
        responseError.setMessage(ex.getMessage());
        responseError.setTraces(this.mapperStackTraces(ex.getStackTrace()));
        return ResponseEntity.status(ex.getStatusCode()).body(responseError);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseErrorBase> exceptionHandler(NullPointerException ex) {
        ResponseErrorBase responseError = new ResponseErrorBase();
        responseError.setMessage(ex.getMessage());
        responseError.setTraces(this.mapperStackTraces(ex.getStackTrace()));
        return ResponseEntity.internalServerError().body(responseError);
    }

    @ExceptionHandler(CustomBusinessException.class)
    public ResponseEntity<ResponseBase<Object>> exceptionHandler(CustomBusinessException ex) {
        ResponseBase<Object> response = new ResponseBase<>();
        response.setCode(ex.getCode());
        response.setDescription(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ResponseErrorBase> exceptionHandler(NumberFormatException ex) {
        ResponseErrorBase responseError = new ResponseErrorBase();
        final String detail = "Failed to convert value of type 'String' to required type number. ";
        responseError.setMessage(detail.concat(ex.getMessage()));
        responseError.setTraces(this.mapperStackTraces(ex.getStackTrace()));
        return ResponseEntity.badRequest().body(responseError);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ResponseErrorBase> exceptionHandler(MissingRequestHeaderException ex) {
        ResponseErrorBase responseError = new ResponseErrorBase();
        responseError.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ResponseErrorBase> exceptionHandler(MissingServletRequestPartException ex) {
        ResponseErrorBase responseError = new ResponseErrorBase();
        responseError.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseBase<Object>> exceptionHandler(IllegalArgumentException ex) {
        ResponseBase<Object> response = new ResponseBase<>();
        response.setCode(ResponseCode.COMMON_ERROR_CODE.intValue());
        response.setDescription(ex.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private List<StackTrace> mapperStackTraces(StackTraceElement[] stackTraceElements) {
        return Arrays
            .stream(stackTraceElements)
            .map(stackTraceElement -> {
                StackTrace trace = new StackTrace();
                trace.setClassName(stackTraceElement.getClassName());
                trace.setMethodName(stackTraceElement.getMethodName());
                trace.setLineNumber(stackTraceElement.getLineNumber());
                return trace;
            })
            .collect(Collectors.toList());
    }
}
