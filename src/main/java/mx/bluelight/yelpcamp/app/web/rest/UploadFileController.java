package mx.bluelight.yelpcamp.app.web.rest;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/upload")
@Slf4j
public class UploadFileController {

    @Autowired
    private FileService fileService;

    @PostMapping(path = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                           @RequestHeader("Folder") String folder,
                                           @RequestPart MultipartFile file) {
        fileService.uploadFile(authorization, folder, file);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFiles(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
                                            @RequestHeader("Folder") String folder,
                                            @RequestPart List<MultipartFile> documents) {
        fileService.uploadFiles(authorization, folder, documents);
        return ResponseEntity.ok().build();
    }
}
