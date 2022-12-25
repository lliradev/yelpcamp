package mx.bluelight.yelpcamp.app.dto;

import com.google.gson.Gson;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommonResponse<T> implements Serializable {
    private static final long serialVersionUID = -7653142642583528894L;
    private Integer code;
    private String description;
    private transient T payload;

    public String toJson() {
        return new Gson().toJson(this);
    }
}
