package com.ehb.javaadvanced.TerroristBE.ingestion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;


/*******************
 * Connects to the external URL and download the excel file
 */

@Service
public class ExcelDownloadService {
    private static final Logger log = LoggerFactory.getLogger(ExcelDownloadService.class);
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public Path downloadExcel(String url, Path target) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse<byte[]> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());

        Files.createDirectories(target.getParent());
        Files.write(target, response.body());
        log.debug("Downloaded excel file from {} to {}", url, target);
        return target;
    }
}
