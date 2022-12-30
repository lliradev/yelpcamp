package mx.bluelight.yelpcamp.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.domain.ContractResponse;
import mx.bluelight.yelpcamp.app.dto.CommonResponse;
import mx.bluelight.yelpcamp.app.exception.CustomBusinessException;
import mx.bluelight.yelpcamp.app.helper.ContractHelper;
import mx.bluelight.yelpcamp.app.service.ContractService;
import mx.bluelight.yelpcamp.app.web.client.ContractWebClient;
import mx.bluelight.yelpcamp.app.web.client.dto.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
class ContractFinder implements ContractService {

    @Autowired
    private ContractWebClient webClient;

    @Autowired
    private ContractHelper contractHelper;

    @Override
    public CommonResponse<List<ContractResponse>> find() {
        ResponseEntity<List<Contract>> contracts = webClient.findAll();

        if (contracts == null || contracts.getBody() == null || contracts.getBody().isEmpty())
            return contractHelper.toResponseEmpty();

        List<Contract> list = contracts
            .getBody()
            .stream()
            .filter(Contract::getActive)
            .collect(Collectors.toList());

        return contractHelper.toResponse(list);
    }

    @Override
    public CommonResponse<ContractResponse> findByContractNumber(Long contractNumber) {
        return this.find()
            .getPayload()
            .stream()
            .filter(contract -> contract.getContractNumber().equals(contractNumber))
            .map(contractResponse -> contractHelper.toResponse(contractResponse))
            .findFirst()
            .orElseThrow(() -> new CustomBusinessException("Contract number not exists", HttpStatus.OK));
    }
}