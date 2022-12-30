package mx.bluelight.yelpcamp.app.helper.impl;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.constant.Sex;
import mx.bluelight.yelpcamp.app.domain.ContractRequest;
import mx.bluelight.yelpcamp.app.domain.ContractResponse;
import mx.bluelight.yelpcamp.app.helper.ContractHelper;
import mx.bluelight.yelpcamp.app.web.client.dto.ContractRequestClient;
import mx.bluelight.yelpcamp.app.web.client.dto.ContractResponseClient;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
class MapperContractHelper implements ContractHelper {

    @Override
    public ContractResponse toResponse(ContractResponse responseClient) {
        ContractResponse response = new ContractResponse();
        response.setContractId(responseClient.getContractId());
        response.setFullName(responseClient.getFullName());
        response.setSex(responseClient.getSex());
        response.setJob(responseClient.getJob());
        response.setBirthdate(responseClient.getBirthdate());
        response.setZipCode(responseClient.getZipCode());
        response.setContractNumber(responseClient.getContractNumber());
        return response;
    }

    @Override
    public ContractResponse toResponse(ContractResponseClient responseClient) {
        ContractResponse response = new ContractResponse();
        response.setContractId(responseClient.getId());
        response.setFullName(responseClient.getFullName());
        response.setSex(this.mapperSex(responseClient.getSex()));
        response.setJob(responseClient.getJob());
        response.setBirthdate(responseClient.getBirthdate());
        response.setZipCode(responseClient.getZipCode());
        response.setContractNumber(responseClient.getContractNumber());
        return response;
    }

    @Override
    public List<ContractResponse> toResponse(List<ContractResponseClient> responseClient) {
        return responseClient
            .stream()
            .map(contract -> {
                ContractResponse contractResponse = new ContractResponse();
                contractResponse.setContractId(contract.getId());
                contractResponse.setFullName(contract.getFullName());
                contractResponse.setSex(this.mapperSex(contract.getSex()));
                contractResponse.setJob(contract.getJob());
                contractResponse.setBirthdate(contract.getBirthdate());
                contractResponse.setZipCode(contract.getZipCode());
                contractResponse.setContractNumber(contract.getContractNumber());
                return contractResponse;
            })
            .collect(Collectors.toList());
    }

    @Override
    public ContractRequestClient toRequestClient(ContractRequest request) {
        ContractRequestClient requestClient = new ContractRequestClient();
        requestClient.setFullName(request.getFullName());
        // requestClient.setSex(request.getSex()); // Refactor
        requestClient.setJob(request.getJob());
        // requestClient.setBirthdate(request.getBirthdate()); // Refactor
        requestClient.setZipCode(request.getZipCode());
        requestClient.setActive(false);
        requestClient.setContractNumber(0L);
        requestClient.setCreatedAt(OffsetDateTime.now());

        return requestClient;
    }

    private Character mapperSex(String sex) {
        return sex.equalsIgnoreCase(Sex.FEMALE.getValue()) ? Sex.FEMALE.getCode() : Sex.MALE.getCode();
    }
}
