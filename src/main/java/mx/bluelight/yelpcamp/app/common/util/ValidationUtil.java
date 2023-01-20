package mx.bluelight.yelpcamp.app.common.util;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class ValidationUtil {

    public boolean isNull(Object object) {
        return Objects.isNull(object);
    }

    public void notNull(@Nullable Object object, String message) {
        Assert.notNull(object, message);
    }

    public <T> boolean isNullOrEmpty(List<T> list) {
        return isNull(list) || list.isEmpty();
    }

    public <T> List<T> emptyList() {
        return Collections.emptyList();
    }
}
