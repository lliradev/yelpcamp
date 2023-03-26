package mx.bluelight.yelpcamp.app.web.rest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/calculator")
@Slf4j
public class CalculatorRestController {

    @PostMapping("/add")
    public ResponseEntity<CalculatorResponse> add(@RequestBody CalculatorEntry calculatorEntry) {
        BigDecimal result = calculatorEntry.getA().add(calculatorEntry.getB());
        CalculatorResponse calculatorResponse = new CalculatorResponse();
        calculatorResponse.setResult(result);
        return ResponseEntity.ok(calculatorResponse);
    }
}

@Data
class CalculatorEntry {
    private BigDecimal a;
    private BigDecimal b;
}

@Data
class CalculatorResponse {
    private BigDecimal result;
}
