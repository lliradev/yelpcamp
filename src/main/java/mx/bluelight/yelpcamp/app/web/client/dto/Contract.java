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
public class Contract implements Serializable {
    private static final long serialVersionUID = -2292234754467387640L;
    private String id;
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
