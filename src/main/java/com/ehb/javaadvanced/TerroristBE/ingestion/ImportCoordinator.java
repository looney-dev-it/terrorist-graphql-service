package com.ehb.javaadvanced.TerroristBE.ingestion;

import com.ehb.javaadvanced.TerroristBE.domain.Terrorist;
import com.ehb.javaadvanced.TerroristBE.parsing.ExcelParser;
import com.ehb.javaadvanced.TerroristBE.persistence.TerroristImportService;
import com.ehb.javaadvanced.TerroristBE.persistence.TerroristRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

@Service
public class ImportCoordinator {
    private static final Logger log = LoggerFactory.getLogger(ImportCoordinator.class);
    @Autowired
    private ExcelDownloadService downloadService;
    @Autowired
    private FileVersionChecker versionChecker;
    @Autowired
    private IngestionProperties ingestionProperties;
    @Autowired
    private FileVersioningService versioningService;
    @Autowired
    private ExcelParser excelParser;
    @Autowired
    private TerroristImportService  importService;

    public void runImport() throws Exception {
        Path newFile = downloadService.downloadExcel(
                        ingestionProperties.getUrl(),
                        Path.of(ingestionProperties.getLocalTerrorFileNew())
                    );
        Path oldFile = Path.of(ingestionProperties.getLocalTerrorFile());

        if(!Files.exists(oldFile) || !versionChecker.isIdentical(oldFile, newFile)){
            log.debug("File replacement needed - oldFile exist : " + Files.exists(oldFile));

            if(Files.exists(oldFile)){
                versioningService.doVersioning();
            } else {
                // In case of first run ...
                Files.move(Path.of(ingestionProperties.getLocalTerrorFileNew()), Path.of(ingestionProperties.getLocalTerrorFile()), StandardCopyOption.REPLACE_EXISTING);
            }
            ArrayList<Terrorist> list = excelParser.parseFile(ingestionProperties.getLocalTerrorFile());
            log.debug("TerrorList parsed : " + list.stream().count() + " entries");
            importService.importTerrorists(list);
            log.debug("Terrorists imported : " + list.stream().count() + " entries");
        } else {
            log.debug("Files are identical, deleting downloaded file");
            Files.delete(newFile);
        }
    }

}
