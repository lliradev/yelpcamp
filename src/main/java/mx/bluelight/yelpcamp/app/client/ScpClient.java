package mx.bluelight.yelpcamp.app.client;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import mx.bluelight.yelpcamp.app.domain.Authorization;
import mx.bluelight.yelpcamp.app.exception.CustomBusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Component
@Slf4j
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
            session = createSession(authorization);
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

    private Session createSession(Authorization authorization) throws JSchException {
        JSch jsch = new JSch();
        // jsch.setKnownHosts("/home/lliradev/.ssh/known_hosts");
        // jsch.setKnownHosts("known_hosts");
        Session session = jsch.getSession(authorization.getUsername(), hostname, port);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(authorization.getPassword());
        session.connect();
        return session;
    }

    private String executeCommand(Authorization authorization, String folder, MultipartFile document) {
        Session session = null;
        ChannelExec exec = null;
        try {
            session = createSession(authorization);

            exec = (ChannelExec) session.openChannel("exec");
            exec.setInputStream(null);
            String destination = String.format("/%s/%s/%s", home, authorization.getUsername(), folder);
            String command = String.format("test -e %s/%s && echo \"File exists\" || echo \"File doesn't exist\"", destination, document.getOriginalFilename());
            exec.setCommand(command);
            InputStream inputStream = exec.getInputStream();
            exec.connect();

            byte[] byteObject = new byte[10240];
            StringBuilder builder = new StringBuilder();
            String result;
            while (!exec.isClosed()) {
                while (inputStream.available() > 0) {
                    int readByte = inputStream.read(byteObject, 0, 1024);
                    if (readByte < 0)
                        break;
                    result = new String(byteObject, 0, readByte);
                    builder.append(result);
                }
                if (exec.isClosed()) break;
            }
            exec.disconnect();
            return builder.toString();
        } catch (JSchException | IOException e) {
            throw new CustomBusinessException(e.getMessage(), HttpStatus.OK);
        } finally {
            if (Objects.nonNull(exec))
                exec.disconnect();
            if (Objects.nonNull(session))
                session.disconnect();
        }
    }
}
