package mx.bluelight.yelpcamp.app.common.web;

import mx.bluelight.yelpcamp.app.common.dto.ResponseBase;
import mx.bluelight.yelpcamp.app.constant.ResponseCode;
import mx.bluelight.yelpcamp.app.constant.ResponseMessage;
import org.springframework.http.ResponseEntity;

public class BaseRestController {

    protected <T> ResponseEntity<ResponseBase<T>> ok(T result) {
        ResponseBase<T> response = new ResponseBase<>();
        response.setCode(ResponseCode.COMMON_SUCCESS_CODE.intValue());
        response.setDescription(ResponseMessage.COMMON_SUCCESS_MESSAGE.getMessage());
        response.setPayload(result);
        return ResponseEntity.ok(response);
    }
}
