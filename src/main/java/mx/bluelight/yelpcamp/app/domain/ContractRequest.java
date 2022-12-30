package mx.bluelight.yelpcamp.app.domain;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContractRequest implements Serializable {
    private static final long serialVersionUID = 2184550053688774632L;
    private String fullName;
    private Character sex;
    private String job;
    private OffsetDateTime birthdate;
    private String zipCode;
    private Long contractNumber;
}
