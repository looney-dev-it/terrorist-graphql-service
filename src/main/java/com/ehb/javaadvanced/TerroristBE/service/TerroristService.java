package com.ehb.javaadvanced.TerroristBE.service;

import com.ehb.javaadvanced.TerroristBE.domain.Terrorist;
import com.ehb.javaadvanced.TerroristBE.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TerroristService {
    private static final Logger log = LoggerFactory.getLogger(TerroristService.class);
    @Autowired
    private TerroristRepository terroristRepository;
    @Autowired
    private TerroristHitRepository hitRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Terrorist> searchAndLogHits(TerroristSearchRequest request) throws AccessDeniedException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user;
        if (auth != null && auth.isAuthenticated()) {
            user = userRepository.findByUsername(auth.getName())
                    .orElse(null);
        } else {
            user = null;
        }

        if(user == null){
            throw new AccessDeniedException("Access denied request ...");
        }

        log.debug("searchAndLogHists executed by user: " + user.getUsername() + " - criteria : " + request.toString());

        List<TerroristEntity> results = switch (request.getType()) {
            case LASTNAME -> terroristRepository.findByLastnameContainingIgnoreCase(request.getValue());
            case FIRSTNAME -> terroristRepository.findByFirstnameContainingIgnoreCase(request.getValue());
            case WHOLENAME -> terroristRepository.findByWholenameContainingIgnoreCase(request.getValue());
            case NRN -> terroristRepository.findByNormalizedNrn(request.getValue());
        };

        /* insert if hit table are records are founds */
        results.forEach(result -> {
            TerroristHitEntity hit = new TerroristHitEntity();
            hit.setHit_criteria(request.toString());
            hit.setHit_date(Date.valueOf(LocalDateTime.now().toLocalDate()));
            hit.setUser(user);
            hit.setHit_content(result.toString());
            hitRepository.save(hit);
        });

        return results.stream()
                .map(TerroristMapper::toDomain)
                .toList();
    }

}
