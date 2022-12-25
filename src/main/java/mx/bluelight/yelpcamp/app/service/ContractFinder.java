package mx.bluelight.yelpcamp.app.service;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.domain.ContractResponse;
import mx.bluelight.yelpcamp.app.dto.CommonResponse;
import mx.bluelight.yelpcamp.app.helper.ContractMapper;
import mx.bluelight.yelpcamp.app.web.client.ContractRestClient;
import mx.bluelight.yelpcamp.app.web.client.dto.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContractFinder {

    @Autowired
    private ContractRestClient restClient;

    @Autowired
    private ContractMapper contractMapper;

    public CommonResponse<List<ContractResponse>> find() {
        ResponseEntity<List<Contract>> contracts = restClient.findAll();

        if (contracts == null || contracts.getBody() == null || contracts.getBody().isEmpty())
            return contractMapper.toResponseEmpty();

        List<Contract> list = contracts
            .getBody()
            .stream()
            .filter(Contract::getActive)
            .collect(Collectors.toList());

        return contractMapper.toResponse(list);
    }
}
