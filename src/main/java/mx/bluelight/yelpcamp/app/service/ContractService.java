package mx.bluelight.yelpcamp.app.service;

import mx.bluelight.yelpcamp.app.domain.ContractResponse;
import mx.bluelight.yelpcamp.app.dto.CommonResponse;

import java.util.List;

public interface ContractService {

    CommonResponse<List<ContractResponse>> find();

    CommonResponse<ContractResponse> findByContractNumber(Long contractNumber);
}
