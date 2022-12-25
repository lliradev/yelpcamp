package mx.bluelight.yelpcamp.app.service;

import mx.bluelight.yelpcamp.app.domain.Contract;
import mx.bluelight.yelpcamp.app.dto.CommonResponse;
import mx.bluelight.yelpcamp.app.helper.ContractMapper;
import mx.bluelight.yelpcamp.app.web.client.ContractRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractFinder {

    @Autowired
    private ContractRestClient restClient;

    @Autowired
    private ContractMapper contractMapper;

    public CommonResponse<List<Contract>> find() {
        ResponseEntity<List<Contract>> contracts = restClient.findAll();

        if (contracts == null || contracts.getBody() == null)
            return contractMapper.toResponseEmpty();

        List<Contract> list = contracts
            .getBody()
            .stream()
            .filter(contract -> contract.getActive().equals(Boolean.TRUE))
            .collect(Collectors.toList());
        return contractMapper.toResponse(list);
    }
}
