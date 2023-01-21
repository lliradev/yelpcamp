package mx.bluelight.yelpcamp.app.client;

import com.jcraft.jsch.*;
import mx.bluelight.yelpcamp.app.domain.Authorization;
import mx.bluelight.yelpcamp.app.exception.CustomBusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Component
public class ScpClient {

    @Value("${app.server.hostname}")
    private String hostname;

    @Value("${app.server.port}")
    private Integer port;

    @Value("${app.server.folderHome}")
    private String home;

    @Value("${app.server.channel}")
    private String channelType;

    public void copyLocalToRemote(Authorization authorization, String folder, MultipartFile document) {
        Session session = null;
        ChannelSftp channel = null;
        try {
            JSch jsch = new JSch();
            // jsch.setKnownHosts("/etc/hosts");
            session = jsch.getSession(authorization.getUsername(), hostname, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(authorization.getPassword());
            session.connect();

            channel = (ChannelSftp) session.openChannel(channelType);
            String destination = String.format("/%s/%s/%s", home, authorization.getUsername(), folder);
            channel.connect();
            channel.cd(destination);

            channel.put(document.getInputStream(), document.getOriginalFilename());
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
