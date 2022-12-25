package mx.bluelight.yelpcamp.app.domain;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contract implements Serializable {
    private static final long serialVersionUID = 4603980047431277848L;
    private String id;
    private String fullName;
    private String sex;
    private String job;
    private OffsetDateTime birthdate;
    private String zipCode;
    private Boolean active;
    private Long contractNumber;
    private OffsetDateTime createdAt;
}
