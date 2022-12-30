package mx.bluelight.yelpcamp.app.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mx.bluelight.yelpcamp.app.common.dto.ResponseBase;
import mx.bluelight.yelpcamp.app.common.web.BaseRestController;
import mx.bluelight.yelpcamp.app.domain.ContractResponse;
import mx.bluelight.yelpcamp.app.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
class ContractRestController extends BaseRestController {

    @Autowired
    private ContractService contractService;

    @GetMapping(produces = {"application/json"})
    @Operation(summary = "Retrieve contracts", description = "Retrieve all contracts actives")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returns list of contracts actives",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ContractResponse.class))))
    })
    public ResponseEntity<ResponseBase<List<ContractResponse>>> find() {
        return ok(contractService.find());
    }

    @GetMapping("/number/{contractNumber}")
    public ResponseEntity<ResponseBase<ContractResponse>> findByContractNumber(@PathVariable Long contractNumber) {
        return ok(contractService.findByContractNumber(contractNumber));
    }
}
