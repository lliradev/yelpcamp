package mx.bluelight.yelpcamp.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.common.util.ValidationUtil;
import mx.bluelight.yelpcamp.app.service.CarService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("advanceCarService")
@Slf4j
public class AdvanceCarService implements CarService {

    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public void drive(int speed, int gear) {
        log.info("Speed car: {}", speed());
        log.info("Speed: {} and gear: {}", speed, gear);
    }

    @Override
    public void drive(int speed) {
        throw new NotImplementedException("Method not implement");
    }

    @Override
    public void drive(Integer gear) {
        validationUtil.notNull(gear, "Gear must not be null");
        // validationUtil.notNull(gear, "Gear is required");

        log.info("Speed car: {}", speed());
        log.info("Gear: {}", gear);
    }
}
