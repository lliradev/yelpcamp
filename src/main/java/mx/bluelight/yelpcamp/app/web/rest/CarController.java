package mx.bluelight.yelpcamp.app.web.rest;

import mx.bluelight.yelpcamp.app.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService simpleCarService;

    @Autowired
    private CarService advanceCarService;

    @GetMapping("/simple")
    public ResponseEntity<Void> simple() {
        simpleCarService.drive(1);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/advance")
    public ResponseEntity<Void> advance() {
        advanceCarService.drive(1, 2);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/advance/speed")
    public ResponseEntity<Void> advanceSpeed() {
        advanceCarService.drive(1);
        return ResponseEntity.noContent().build();
    }
}
