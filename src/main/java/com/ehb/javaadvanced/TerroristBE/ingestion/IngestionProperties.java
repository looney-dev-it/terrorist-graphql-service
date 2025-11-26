package com.ehb.javaadvanced.TerroristBE.ingestion;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.ingestion")
public class IngestionProperties {
    private String url;
    private String localTerrorFile;
    private String localTerrorFileNew;
    private String archiveFolder;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getLocalTerrorFile() { return localTerrorFile; }
    public void setLocalTerrorFile(String localTerrorFile) { this.localTerrorFile = localTerrorFile; }
    public String getArchiveFolder() { return archiveFolder; }
    public void setArchiveFolder(String archiveFolder) { this.archiveFolder = archiveFolder; }
    public String getLocalTerrorFileNew() { return localTerrorFileNew; }
    public void setLocalTerrorFileNew(String localTerrorFileNew) {this.localTerrorFileNew = localTerrorFileNew; }
}
