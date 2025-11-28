package com.ehb.javaadvanced.TerroristBE.graphql;

import com.ehb.javaadvanced.TerroristBE.domain.Terrorist;
import com.ehb.javaadvanced.TerroristBE.persistence.NrnUtils;
import com.ehb.javaadvanced.TerroristBE.persistence.TerroristMapper;
import com.ehb.javaadvanced.TerroristBE.persistence.TerroristRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TerroristController {
    private static final Logger log = LoggerFactory.getLogger(TerroristController.class);

    @Autowired
    private TerroristRepository repository;

    @QueryMapping
    public List<Terrorist> terroristsByLastname(@Argument String lastname){
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();*/
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            String username = auth.getName();
            log.info("Query executed by user: {}", username);
        } else {
            log.warn("Query executed without authentication");
        }
        // log.debug("Query terroristsByLastname executed by user: " + username);
        return repository.findByLastnameContainingIgnoreCase(lastname)
                .stream()
                .map(TerroristMapper::toDomain)
                .toList();
    }

    @QueryMapping
    public List<Terrorist> terroristsByFirstname(@Argument String firstname){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        log.debug("Query terroristsByFirstname executed by user: " + username);
        return repository.findByFirstnameContainingIgnoreCase(firstname)
                .stream()
                .map(TerroristMapper::toDomain)
                .toList();
    }

    @QueryMapping
    public List<Terrorist> terroristsByWholename(@Argument String wholename){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        log.debug("Query terroristsByWholename executed by user: " + username);
        return repository.findByWholenameContainingIgnoreCase(wholename)
                .stream()
                .map(TerroristMapper::toDomain)
                .toList();
    }

    @QueryMapping
    public List<Terrorist> terroristsByNrn(@Argument String nrn){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        log.debug("Query terroristsByNrn executed by user: " + username);
        String normalized = NrnUtils.normalize(nrn); // remove all chars, only numbers
        return repository.searchByNormalizedNrn(normalized)
                .stream()
                .map(TerroristMapper::toDomain)
                .toList();
    }

}
