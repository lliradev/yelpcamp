package mx.bluelight.yelpcamp.app.web.client;

import mx.bluelight.yelpcamp.app.web.client.dto.Contract;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContractRestClient {

    ResponseEntity<List<Contract>> findAll();
}
