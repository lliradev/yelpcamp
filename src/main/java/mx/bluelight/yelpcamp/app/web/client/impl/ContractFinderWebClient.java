package mx.bluelight.yelpcamp.app.web.client.impl;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.web.client.ContractWebClient;
import mx.bluelight.yelpcamp.app.web.client.dto.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
class ContractFinderWebClient implements ContractWebClient {

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Value("${spring.application.insurance.contract}")
    private String url;

    @Override
    public ResponseEntity<List<Contract>> findAll() {
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
