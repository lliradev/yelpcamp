package mx.bluelight.yelpcamp.app.web.rest;

import mx.bluelight.yelpcamp.app.domain.Contract;
import mx.bluelight.yelpcamp.app.dto.CommonResponse;
import mx.bluelight.yelpcamp.app.service.ContractFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContractRestController {

    @Autowired
    private ContractFinder contractFinder;

    @GetMapping("/contracts")
    public ResponseEntity<CommonResponse<List<Contract>>> find() {
        return ResponseEntity.ok(contractFinder.find());
    }
}