package mx.bluelight.yelpcamp.app.service;

import mx.bluelight.yelpcamp.app.domain.ContractResponse;

import java.util.List;

public interface ContractService {

    List<ContractResponse> find();

    ContractResponse findByContractNumber(Long contractNumber);
}
