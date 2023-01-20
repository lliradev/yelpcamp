package mx.bluelight.yelpcamp.app.common.dto;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseErrorBase implements Serializable {
    private static final long serialVersionUID = -745399347174804245L;
    private UUID requestId = UUID.randomUUID();
    private String message;
    private OffsetDateTime timestamp = OffsetDateTime.now();
    private List<StackTrace> traces = Collections.emptyList();
}
