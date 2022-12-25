package mx.bluelight.yelpcamp.app.constant;

import lombok.Getter;

@Getter
public enum Sex {
    MALE('M', "Male"),
    FEMALE('F', "Female");

    private final Character code;
    private final String value;

    Sex(Character code, String value) {
        this.code = code;
        this.value = value;
    }
}
