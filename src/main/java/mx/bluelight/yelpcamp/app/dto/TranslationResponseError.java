package mx.bluelight.yelpcamp.app.dto;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TranslationResponseError implements Serializable {
    private static final long serialVersionUID = -745399347174804245L;
    private String message;
    private OffsetDateTime timestamp;
    private StackTraceElement[] stackTraceElements;
}
