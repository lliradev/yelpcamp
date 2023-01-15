package mx.bluelight.yelpcamp.app.domain;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car implements Serializable {
    private static final long serialVersionUID = -1944661665890597764L;
    private String type;
    private String model;
    private String color;
    private Integer speed;
}
