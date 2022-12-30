package mx.bluelight.yelpcamp.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.domain.ContractRequest;
import mx.bluelight.yelpcamp.app.domain.ContractResponse;
import mx.bluelight.yelpcamp.app.exception.CustomBusinessException;
import mx.bluelight.yelpcamp.app.helper.ContractHelper;
import mx.bluelight.yelpcamp.app.service.ContractService;
import mx.bluelight.yelpcamp.app.web.client.ContractWebClient;
import mx.bluelight.yelpcamp.app.web.client.dto.ContractResponseClient;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("contractFinder")
@Slf4j
class FinderContractService implements ContractService {

    @Autowired
    private ContractWebClient contractFinderWebClient;

    @Autowired
    private ContractHelper contractHelper;

    @Override
    public List<ContractResponse> find() {
        ResponseEntity<List<ContractResponseClient>> contracts = contractFinderWebClient.findAll();

        if (contracts == null || contracts.getBody() == null || contracts.getBody().isEmpty())
            return contractHelper.toResponse(new ArrayList<>());

        List<ContractResponseClient> list = contracts
            .getBody()
            .stream()
            .filter(ContractResponseClient::getActive)
            .collect(Collectors.toList());

        return contractHelper.toResponse(list);
    }

    @Override
    public ContractResponse findByContractNumber(Long contractNumber) {
        return this.find()
            .stream()
            .filter(contract -> contract.getContractNumber().equals(contractNumber))
            .map(contractResponse -> contractHelper.toResponse(contractResponse))
            .findFirst()
            .orElseThrow(() -> new CustomBusinessException("Contract number not exists", HttpStatus.OK));
    }

    @Override
    public String create(ContractRequest request) {
        throw new NotImplementedException("Method not implement");
    }
}
