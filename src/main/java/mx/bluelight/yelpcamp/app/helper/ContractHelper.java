package mx.bluelight.yelpcamp.app.helper;

import mx.bluelight.yelpcamp.app.domain.ContractResponse;
import mx.bluelight.yelpcamp.app.web.client.dto.Contract;

import java.util.List;

public interface ContractHelper {

    ContractResponse toResponse(ContractResponse responseClient);

    List<ContractResponse> toResponse(List<Contract> responseClient);
}
