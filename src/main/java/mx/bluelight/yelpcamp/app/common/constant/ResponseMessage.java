package mx.bluelight.yelpcamp.app.common.constant;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    COMMON_SUCCESS_MESSAGE("Successful request"),
    COMMON_ERROR_MESSAGE("Failed request");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }
}
