package mx.bluelight.yelpcamp.app.domain;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContractResponse implements Serializable {
    private static final long serialVersionUID = 4603980047431277848L;
    private String contractId;
    private String fullName;
    private Character sex;
    private String job;
    private OffsetDateTime birthdate;
    private String zipCode;
    private Long contractNumber;
}
