package com.ehb.javaadvanced.TerroristBE.ingestion;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.Arrays;

/*************$$
 * Validate that files are identical or not - binary check !
 */

@Service
public class FileVersionChecker {

    public boolean isIdentical(Path oldFile, Path newFile) throws Exception {
        return Arrays.equals(sha256(oldFile), sha256(newFile));
    }

    private byte[] sha256(Path path) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (InputStream is = Files.newInputStream(path)) {
            byte[] buffer = new byte[8192];
            int read;
            while ((read = is.read(buffer)) > 0) {
                md.update(buffer, 0, read);
            }
        }
        return md.digest();
    }
}
