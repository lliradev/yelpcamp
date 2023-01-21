package mx.bluelight.yelpcamp.app.domain;

import com.google.gson.Gson;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Authorization implements Serializable {
    private static final long serialVersionUID = -3170684201741467651L;
    private String username;
    private String password;

    public String toJson() {
        return new Gson().toJson(this);
    }
}
