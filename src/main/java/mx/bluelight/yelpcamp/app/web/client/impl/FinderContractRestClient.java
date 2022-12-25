package mx.bluelight.yelpcamp.app.web.client.impl;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.web.client.ContractRestClient;
import mx.bluelight.yelpcamp.app.web.client.dto.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
class FinderContractRestClient implements ContractRestClient {

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<List<Contract>> findAll() {
        final String url = "https://63975c7a86d04c7633935b72.mockapi.io/api/insurance/contract";
        HttpEntity<?> empty = HttpEntity.EMPTY;
        long start = System.nanoTime();
        ResponseEntity<List<Contract>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, empty,
            new ParameterizedTypeReference<List<Contract>>() {
            });
        long end = System.nanoTime();
        log.info("Request duration in Ms: {}", ((end - start) / 1_000_000));
        return responseEntity;
    }
}
