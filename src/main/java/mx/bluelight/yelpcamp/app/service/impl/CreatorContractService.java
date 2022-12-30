package mx.bluelight.yelpcamp.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.domain.ContractRequest;
import mx.bluelight.yelpcamp.app.domain.ContractResponse;
import mx.bluelight.yelpcamp.app.exception.CustomBusinessException;
import mx.bluelight.yelpcamp.app.helper.ContractHelper;
import mx.bluelight.yelpcamp.app.service.ContractService;
import mx.bluelight.yelpcamp.app.web.client.ContractWebClient;
import mx.bluelight.yelpcamp.app.web.client.dto.ContractRequestClient;
import mx.bluelight.yelpcamp.app.web.client.dto.ContractResponseClient;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("contractCreator")
@Slf4j
public class CreatorContractService implements ContractService {

    @Autowired
    private ContractWebClient contractCreatorWebClient;

    @Autowired
    private ContractHelper contractHelper;

    @Override
    public List<ContractResponse> find() {
        throw new NotImplementedException("Method not implement");
    }

    @Override
    public ContractResponse findByContractNumber(Long contractNumber) {
        throw new NotImplementedException("Method not implement");
    }

    @Override
    public String create(ContractRequest request) {
        ContractRequestClient requestClient = contractHelper.toRequestClient(request);
        ResponseEntity<ContractResponseClient> responseClient = contractCreatorWebClient.save(requestClient);
        if (!responseClient.hasBody() || responseClient.getBody().getId() == null || responseClient.getBody().getId().isEmpty())
            throw new CustomBusinessException("Error to create contract", HttpStatus.OK);
        return responseClient.getBody().getId();
    }
}
