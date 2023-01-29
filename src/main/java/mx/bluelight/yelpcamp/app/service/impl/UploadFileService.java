package mx.bluelight.yelpcamp.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.client.ScpClient;
import mx.bluelight.yelpcamp.app.constants.Folder;
import mx.bluelight.yelpcamp.app.domain.Authorization;
import mx.bluelight.yelpcamp.app.exception.CustomBusinessException;
import mx.bluelight.yelpcamp.app.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
class UploadFileService implements FileService {

    @Autowired
    private ScpClient scpClient;

    @Override
    public void uploadFile(String authorization, Folder folder, MultipartFile file) {
        Optional<Authorization> credentials = retrieveCredentials(authorization);

        Authorization auth = credentials.orElseThrow(() -> new CustomBusinessException("Credentials is not present",
            HttpStatus.BAD_REQUEST));

        scpClient.copyLocalToRemote(auth, folder, file);
    }

    @Override
    public void uploadFiles(String authorization, Folder folder, List<MultipartFile> files) {
        Optional<Authorization> credentials = retrieveCredentials(authorization);

        Authorization auth = credentials.orElseThrow(() -> new CustomBusinessException("Credentials is not present",
            HttpStatus.BAD_REQUEST));

        files.forEach(multipartFile -> scpClient.copyLocalToRemote(auth, folder, multipartFile));
    }

    private Optional<Authorization> retrieveCredentials(String authorization) {
        if (authorization == null || !authorization.toLowerCase().startsWith("basic")) return Optional.empty();

        String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] decode = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(decode, StandardCharsets.UTF_8);
        String[] result = credentials.split(":");

        Authorization response = new Authorization();
        response.setUsername(result[0]);
        response.setPassword(result[1]);
        return Optional.of(response);
    }
}
