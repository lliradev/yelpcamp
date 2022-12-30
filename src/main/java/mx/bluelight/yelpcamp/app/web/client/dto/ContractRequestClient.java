package mx.bluelight.yelpcamp.app.web.client.dto;

import com.google.gson.Gson;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContractRequestClient implements Serializable {
    private static final long serialVersionUID = -2815199898918060355L;
    private String fullName;
    private String sex;
    private String job;
    private OffsetDateTime birthdate;
    private String zipCode;
    private Boolean active;
    private Long contractNumber;
    private OffsetDateTime createdAt;

    public String toJson() {
        return new Gson().toJson(this);
    }
}
