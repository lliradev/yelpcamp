package mx.bluelight.yelpcamp.app.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    void uploadFile(String authorization, String folder, MultipartFile file);

    void uploadFiles(String authorization, String folder, List<MultipartFile> files);
}
