package mx.bluelight.yelpcamp.app.constant;

import lombok.Getter;

@Getter
public enum ResponseCode {

    COMMON_SUCCESS_CODE(0),
    COMMON_ERROR_CODE(-1);

    private final Number code;

    ResponseCode(Number code) {
        this.code = code;
    }

    public Integer intValue() {
        return code.intValue();
    }
}
