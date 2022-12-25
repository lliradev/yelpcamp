package mx.bluelight.yelpcamp.app.exception;

import lombok.Getter;
import mx.bluelight.yelpcamp.app.constant.ResponseCode;
import org.springframework.http.HttpStatus;

@Getter
public class CustomBusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String message;
    private final HttpStatus httpStatus;
    private final int code;

    public CustomBusinessException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
        this.code = ResponseCode.COMMON_ERROR_CODE.intValue();
    }
}
