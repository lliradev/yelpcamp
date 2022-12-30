package mx.bluelight.yelpcamp.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Global response")
public class CommonResponse<T> implements Serializable {
    private static final long serialVersionUID = -7653142642583528894L;
    @Schema(description = "Response code", example = "0")
    private Integer code;
    @Schema(description = "Response description", example = "Successful request")
    private String description;
    @Schema(description = "Response payload")
    private transient T payload;
}
