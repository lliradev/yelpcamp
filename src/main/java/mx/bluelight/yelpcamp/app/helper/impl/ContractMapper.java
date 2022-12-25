package mx.bluelight.yelpcamp.app.helper.impl;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.constant.ResponseCode;
import mx.bluelight.yelpcamp.app.constant.ResponseMessage;
import mx.bluelight.yelpcamp.app.constant.Sex;
import mx.bluelight.yelpcamp.app.domain.ContractResponse;
import mx.bluelight.yelpcamp.app.dto.CommonResponse;
import mx.bluelight.yelpcamp.app.helper.ContractHelper;
import mx.bluelight.yelpcamp.app.web.client.dto.Contract;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
class ContractMapper implements ContractHelper {

    @Override
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

        response.setCode(ResponseCode.COMMON_SUCCESS_CODE.intValue());
        response.setDescription(ResponseMessage.COMMON_SUCCESS_MESSAGE.getMessage());
        response.setPayload(contractResponse);
        return response;
    }

    @Override
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

        response.setCode(ResponseCode.COMMON_SUCCESS_CODE.intValue());
        response.setDescription(ResponseMessage.COMMON_SUCCESS_MESSAGE.getMessage());
        response.setPayload(contracts);

        return response;
    }

    @Override
    public CommonResponse<List<ContractResponse>> toResponseEmpty() {
        CommonResponse<List<ContractResponse>> response = new CommonResponse<>();

        response.setCode(ResponseCode.COMMON_SUCCESS_CODE.intValue());
        response.setDescription(ResponseMessage.COMMON_SUCCESS_MESSAGE.getMessage());
        response.setPayload(new ArrayList<>());

        return response;
    }

    private Character mapperSex(String sex) {
        return sex.equalsIgnoreCase(Sex.FEMALE.getValue()) ? Sex.FEMALE.getCode() : Sex.MALE.getCode();
    }
}
