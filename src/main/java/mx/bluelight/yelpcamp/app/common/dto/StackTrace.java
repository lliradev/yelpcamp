package mx.bluelight.yelpcamp.app.common.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StackTrace implements Serializable {
    private static final long serialVersionUID = 904729633210699624L;
    private String className;
    private String methodName;
    private Integer lineNumber;
}
