package com.ehb.javaadvanced.TerroristBE.persistence;

import com.ehb.javaadvanced.TerroristBE.domain.Terrorist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TerroristImportService {
    private static final Logger log = LoggerFactory.getLogger(TerroristImportService.class);
    @Autowired
    private TerroristRepository  repository;

    public void importTerrorists(ArrayList<Terrorist> terrorists){
        log.debug("Streaming Terrorists list : " + terrorists.size());
        List<TerroristEntity> entities = terrorists.stream()
                .map(TerroristMapper::toEntity)
                .toList();
        log.debug("Saving Terrorists list : " + entities.size());
        repository.saveAll(entities);
    }
}
