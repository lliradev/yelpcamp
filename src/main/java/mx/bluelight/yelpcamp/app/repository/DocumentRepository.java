package mx.bluelight.yelpcamp.app.repository;

import mx.bluelight.yelpcamp.app.domain.Authorization;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentRepository {

    void copyLocalToRemote(Authorization authorization, MultipartFile document);
}
