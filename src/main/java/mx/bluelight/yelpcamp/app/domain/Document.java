package mx.bluelight.yelpcamp.app.domain;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Document implements Serializable {
    private static final long serialVersionUID = -1972612813946389799L;
    private String documentFile;
    private String user;
    private String password;
}
