package mx.bluelight.yelpcamp.app.helper;

import mx.bluelight.yelpcamp.app.domain.Contract;
import mx.bluelight.yelpcamp.app.dto.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContractMapper {

    private static final String SUCCESS = "Successful request";
    private static final Number NUMBER_ZERO = 0;

    public CommonResponse<List<Contract>> toResponse(List<Contract> contracts) {
        CommonResponse<List<Contract>> response = new CommonResponse<>();

        response.setCode(NUMBER_ZERO.intValue());
        response.setDescription(SUCCESS);
        response.setPayload(contracts);

        return response;
    }

    public CommonResponse<List<Contract>> toResponseEmpty() {
        CommonResponse<List<Contract>> response = new CommonResponse<>();

        response.setCode(NUMBER_ZERO.intValue());
        response.setDescription(SUCCESS);
        response.setPayload(new ArrayList<>());

        return response;
    }
}
