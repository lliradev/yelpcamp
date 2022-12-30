package mx.bluelight.yelpcamp.app.web.client.impl;

import mx.bluelight.yelpcamp.app.common.web.BaseWebClient;
import mx.bluelight.yelpcamp.app.web.client.ContractWebClient;
import mx.bluelight.yelpcamp.app.web.client.dto.ContractRequestClient;
import mx.bluelight.yelpcamp.app.web.client.dto.ContractResponseClient;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Qualifier("contractFinderWebClient")
class FinderContractWebClient extends BaseWebClient implements ContractWebClient {

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Value("${app.insurance.contract}")
    private String url;

    @Override
    public ResponseEntity<List<ContractResponseClient>> findAll() {
        HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(getHeaders());
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<ContractResponseClient>>() {
        });
    }

    @Override
    public ResponseEntity<ContractResponseClient> save(ContractRequestClient requestClient) {
        throw new NotImplementedException("Method not implement");
    }
}
