package mx.bluelight.yelpcamp.app.web.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/simple")
    public ResponseEntity<Map<String, Object>> getUser(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage,
                                                       @RequestHeader("X-User-App") String userApp,
                                                       @RequestHeader("X-Status-App") Boolean status) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("language", acceptLanguage);
        headers.put("user", userApp);
        headers.put("status", status);
        return ResponseEntity.ok(headers);
    }
}
