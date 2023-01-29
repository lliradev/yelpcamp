package mx.bluelight.yelpcamp.app.service;

import mx.bluelight.yelpcamp.app.constants.Folder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    void uploadFile(String authorization, Folder folder, MultipartFile file);

    void uploadFiles(String authorization, Folder folder, List<MultipartFile> files);
}
