package com.ehb.javaadvanced.TerroristBE.ingestion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/********************************
 * Manage to version the terrorist list if a download retrieves a newer version
 * Philosophy :
 *         Active file : terror-list-be.xlsx
 *         Archive file : terror-list-be-<date>.xlsx
 *         New file : terror-list-be-new.xlsx
 */


@Service
public class FileVersioningService {

    private static final Logger log = LoggerFactory.getLogger(FileVersioningService.class);

    @Autowired IngestionProperties ingestionProperties;

    public void doVersioning() throws IOException {
        Files.createDirectories(Path.of(ingestionProperties.getArchiveFolder()));
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        Path archiveFolder = Paths.get(ingestionProperties.getArchiveFolder());
        Path archiveFile = archiveFolder.resolve("terror-list-be-" + formattedDate + ".xlsx");

        Files.move(Path.of(ingestionProperties.getLocalTerrorFile()),
                archiveFile,
                StandardCopyOption.REPLACE_EXISTING);

        log.debug("Archive file has been created: " + archiveFile.toString());
        Files.move(Path.of(ingestionProperties.getLocalTerrorFileNew()), Path.of(ingestionProperties.getLocalTerrorFile()), StandardCopyOption.REPLACE_EXISTING);
        log.debug("New terrorist file is placed : " + ingestionProperties.getLocalTerrorFile());
    }
}
