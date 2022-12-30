package mx.bluelight.yelpcamp.app.service;

public interface CarService {

    Integer SPEED_DEFAULT = 10;

    void drive(int speed, int gear);

    void drive(int speed);

    default String speed() {
        return "Speed car";
    }
}