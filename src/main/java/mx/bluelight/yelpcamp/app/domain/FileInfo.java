package mx.bluelight.yelpcamp.app.domain;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileInfo implements Serializable {
    private static final long serialVersionUID = -1972612813946389799L;
    private String fileName;
    private String status;
}
