package com.hina.dto.response;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

import static com.hina.constant.GameConst.FILE_AUTH_INFO;

public interface AuthInterface extends ResponseInterface {
    default void saveAuthInfo(String username, String password) throws IOException {
        File file = new File(FILE_AUTH_INFO);
        String basicAuth = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("Could not create file");
            }
        }
        if (file.exists() && file.canWrite()) {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            fw.write(basicAuth);
            fw.close();
        }
    }
}
