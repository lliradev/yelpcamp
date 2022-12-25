package mx.bluelight.yelpcamp.app.helper;

import mx.bluelight.yelpcamp.app.domain.ContractResponse;
import mx.bluelight.yelpcamp.app.dto.CommonResponse;
import mx.bluelight.yelpcamp.app.web.client.dto.Contract;

import java.util.List;

public interface ContractHelper {

    CommonResponse<ContractResponse> toResponse(ContractResponse responseClient);

    CommonResponse<List<ContractResponse>> toResponse(List<Contract> responseClient);

    CommonResponse<List<ContractResponse>> toResponseEmpty();
}
