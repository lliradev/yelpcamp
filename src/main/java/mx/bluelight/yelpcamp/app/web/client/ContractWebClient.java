package mx.bluelight.yelpcamp.app.web.client;

import mx.bluelight.yelpcamp.app.web.client.dto.ContractRequestClient;
import mx.bluelight.yelpcamp.app.web.client.dto.ContractResponseClient;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContractWebClient {

    ResponseEntity<List<ContractResponseClient>> findAll();

    ResponseEntity<ContractResponseClient> save(ContractRequestClient requestClient);
}
