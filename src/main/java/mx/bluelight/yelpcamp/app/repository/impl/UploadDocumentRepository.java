package mx.bluelight.yelpcamp.app.repository.impl;

import com.jcraft.jsch.*;
import mx.bluelight.yelpcamp.app.domain.Authorization;
import mx.bluelight.yelpcamp.app.exception.CustomBusinessException;
import mx.bluelight.yelpcamp.app.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Component
class UploadDocumentRepository implements DocumentRepository {

    private static final String PATH = "/Documents";
    private static final String CMD = "scp foobar.txt username@remotehost:/some/remote/directory";
    @Value("${app.server.hostname}")
    private String hostname;

    @Value("${app.server.port}")
    private Integer port;

    @Override
    public void copyLocalToRemote(Authorization authorization, MultipartFile document) {
        Session session = null;
        ChannelSftp channel = null;
        try {
            JSch jsch = new JSch();
            jsch.setKnownHosts("/etc/hosts");
            session = jsch.getSession(authorization.getUsername(), hostname, port);
            session.setPassword(authorization.getPassword());

            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();

            String destination = String.format("/home/%s/Documents", authorization.getUsername());
            channel.put(document.getInputStream(), destination);
        } catch (JSchException | SftpException | IOException e) {
            throw new CustomBusinessException(e.getMessage(), HttpStatus.OK);
        } finally {
            if (Objects.nonNull(channel))
                channel.disconnect();
            if (Objects.nonNull(session))
                session.disconnect();
        }
    }
}
