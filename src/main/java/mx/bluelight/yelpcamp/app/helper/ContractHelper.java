package mx.bluelight.yelpcamp.app.helper;

import mx.bluelight.yelpcamp.app.domain.ContractRequest;
import mx.bluelight.yelpcamp.app.domain.ContractResponse;
import mx.bluelight.yelpcamp.app.web.client.dto.ContractRequestClient;
import mx.bluelight.yelpcamp.app.web.client.dto.ContractResponseClient;

import java.util.List;

public interface ContractHelper {

    ContractResponse toResponse(ContractResponse responseClient);

    ContractResponse toResponse(ContractResponseClient responseClient);

    List<ContractResponse> toResponse(List<ContractResponseClient> responseClient);

    ContractRequestClient toRequestClient(ContractRequest request);
}
