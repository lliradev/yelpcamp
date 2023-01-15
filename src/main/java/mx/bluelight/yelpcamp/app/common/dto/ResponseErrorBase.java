package mx.bluelight.yelpcamp.app.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class ResponseErrorBase implements Serializable {
    private static final long serialVersionUID = -745399347174804245L;
    private UUID requestId;
    private String message;
    private OffsetDateTime timestamp;
    private List<StackTrace> traces;

    public ResponseErrorBase() {
        this.requestId = UUID.randomUUID();
        this.timestamp = OffsetDateTime.now();
        this.traces = Collections.emptyList();
    }
}
