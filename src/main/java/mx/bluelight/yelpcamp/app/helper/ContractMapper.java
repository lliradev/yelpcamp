package mx.bluelight.yelpcamp.app.helper;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.constant.Sex;
import mx.bluelight.yelpcamp.app.domain.ContractResponse;
import mx.bluelight.yelpcamp.app.dto.CommonResponse;
import mx.bluelight.yelpcamp.app.web.client.dto.Contract;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ContractMapper {

    private static final String SUCCESS = "Successful request";
    private static final Number NUMBER_ZERO = 0;

    public CommonResponse<ContractResponse> toResponse(ContractResponse responseClient) {
        CommonResponse<ContractResponse> response = new CommonResponse<>();

        ContractResponse contractResponse = new ContractResponse();
        contractResponse.setContractId(responseClient.getContractId());
        contractResponse.setFullName(responseClient.getFullName());
        contractResponse.setSex(responseClient.getSex());
        contractResponse.setJob(responseClient.getJob());
        contractResponse.setBirthdate(responseClient.getBirthdate());
        contractResponse.setZipCode(responseClient.getZipCode());
        contractResponse.setContractNumber(responseClient.getContractNumber());

        response.setCode(NUMBER_ZERO.intValue());
        response.setDescription(SUCCESS);
        response.setPayload(contractResponse);
        return response;
    }

    public CommonResponse<List<ContractResponse>> toResponse(List<Contract> responseClient) {
        CommonResponse<List<ContractResponse>> response = new CommonResponse<>();

        List<ContractResponse> contracts = responseClient
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

        response.setCode(NUMBER_ZERO.intValue());
        response.setDescription(SUCCESS);
        response.setPayload(contracts);

        return response;
    }

    public CommonResponse<List<ContractResponse>> toResponseEmpty() {
        CommonResponse<List<ContractResponse>> response = new CommonResponse<>();

        response.setCode(NUMBER_ZERO.intValue());
        response.setDescription(SUCCESS);
        response.setPayload(new ArrayList<>());

        return response;
    }

    private Character mapperSex(String sex) {
        return sex.equalsIgnoreCase(Sex.FEMALE.getValue()) ? Sex.FEMALE.getCode() : Sex.MALE.getCode();
    }
}
