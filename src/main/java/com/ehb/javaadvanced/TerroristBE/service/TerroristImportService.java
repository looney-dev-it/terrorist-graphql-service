package com.ehb.javaadvanced.TerroristBE.service;

import com.ehb.javaadvanced.TerroristBE.domain.Terrorist;
import com.ehb.javaadvanced.TerroristBE.persistence.TerroristEntity;
import com.ehb.javaadvanced.TerroristBE.persistence.TerroristMapper;
import com.ehb.javaadvanced.TerroristBE.persistence.TerroristRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/*******************************
 * Service to manage the import of the Excel parsed Terrorist list to the database.
 * If a new list is available -> it will first delete the content of the current table
 *  before inserting the new records.
 */

@Service
public class TerroristImportService {
    private static final Logger log = LoggerFactory.getLogger(TerroristImportService.class);
    @Autowired
    private TerroristRepository repository;

    public void importTerrorists(ArrayList<Terrorist> terrorists){
        log.debug("Streaming Terrorists list : " + terrorists.size());

        long count = repository.count();
        if (count > 0) {
            log.debug("Existing records found: " + count + " → deleting before import");
            repository.deleteAllInBatch();
        }

        List<TerroristEntity> entities = terrorists.stream()
                .map(TerroristMapper::toEntity)
                .toList();
        log.debug("Saving Terrorists list : " + entities.size());
        repository.saveAll(entities);
    }
}
