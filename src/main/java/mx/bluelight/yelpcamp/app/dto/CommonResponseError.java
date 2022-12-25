package mx.bluelight.yelpcamp.app.dto;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonResponseError implements Serializable {
    private static final long serialVersionUID = -745399347174804245L;
    private UUID requestId = UUID.randomUUID();
    private String message;
    private OffsetDateTime timestamp;
    private List<StackTrace> traces;
}
