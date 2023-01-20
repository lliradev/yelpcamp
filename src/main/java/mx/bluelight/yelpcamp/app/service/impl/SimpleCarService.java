package mx.bluelight.yelpcamp.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.service.CarService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("simpleCarService")
@Slf4j
public class SimpleCarService implements CarService {

    @Override
    public void drive(int speed, int gear) {
        throw new NotImplementedException("Method not implement");
    }

    @Override
    public void drive(int speed) {
        log.info("Speed default: {}", SPEED_DEFAULT);
        log.info("Speed: {}", speed);
    }

    @Override
    public void drive(Integer gear) {
        throw new NotImplementedException("Method not implement");
    }
}
