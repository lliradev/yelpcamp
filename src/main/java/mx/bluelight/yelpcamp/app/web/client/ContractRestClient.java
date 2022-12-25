package mx.bluelight.yelpcamp.app.web.client;

import mx.bluelight.yelpcamp.app.domain.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ContractRestClient {

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    public ResponseEntity<List<Contract>> findAll() {
        final String url = "https://63975c7a86d04c7633935b72.mockapi.io/api/insurance/contracts";
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Contract>>() {
        });
    }
}
