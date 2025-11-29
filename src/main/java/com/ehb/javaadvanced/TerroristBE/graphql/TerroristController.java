package com.ehb.javaadvanced.TerroristBE.graphql;

import com.ehb.javaadvanced.TerroristBE.domain.Terrorist;
import com.ehb.javaadvanced.TerroristBE.persistence.NrnUtils;
import com.ehb.javaadvanced.TerroristBE.persistence.TerroristMapper;
import com.ehb.javaadvanced.TerroristBE.persistence.TerroristRepository;
import com.ehb.javaadvanced.TerroristBE.service.TerroristSearchRequest;
import com.ehb.javaadvanced.TerroristBE.service.TerroristSearchType;
import com.ehb.javaadvanced.TerroristBE.service.TerroristService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Controller
public class TerroristController {
    private static final Logger log = LoggerFactory.getLogger(TerroristController.class);

    @Autowired
    private TerroristService service;

    @QueryMapping
    public List<Terrorist> terroristsByLastname(@Argument String lastname) throws AccessDeniedException {

        return service.searchAndLogHits(new TerroristSearchRequest(
                TerroristSearchType.LASTNAME,
                lastname));
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
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
                .toList();*/
    }

    @QueryMapping
    public List<Terrorist> terroristsByFirstname(@Argument String firstname) throws AccessDeniedException {
        return service.searchAndLogHits(new TerroristSearchRequest(
                TerroristSearchType.FIRSTNAME,
                firstname));
    }

    @QueryMapping
    public List<Terrorist> terroristsByWholename(@Argument String wholename) throws AccessDeniedException {
        return service.searchAndLogHits(new TerroristSearchRequest(
                TerroristSearchType.WHOLENAME,
                wholename));
    }

    @QueryMapping
    public List<Terrorist> terroristsByNrn(@Argument String nrn) throws AccessDeniedException {
        String normalized = NrnUtils.normalize(nrn); // remove all chars, only numbers
        return service.searchAndLogHits(new TerroristSearchRequest(
                TerroristSearchType.NRN,
                normalized));
    }

}
