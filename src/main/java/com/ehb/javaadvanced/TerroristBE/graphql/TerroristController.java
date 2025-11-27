package com.ehb.javaadvanced.TerroristBE.graphql;

import com.ehb.javaadvanced.TerroristBE.domain.Terrorist;
import com.ehb.javaadvanced.TerroristBE.persistence.NrnUtils;
import com.ehb.javaadvanced.TerroristBE.persistence.TerroristMapper;
import com.ehb.javaadvanced.TerroristBE.persistence.TerroristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TerroristController {

    @Autowired
    private TerroristRepository repository;

    @QueryMapping
    public List<Terrorist> terroristsByLastname(@Argument String lastname){
        return repository.findByLastnameContainingIgnoreCase(lastname)
                .stream()
                .map(TerroristMapper::toDomain)
                .toList();
    }

    @QueryMapping
    public List<Terrorist> terroristsByFirstname(@Argument String firstname){
        return repository.findByFirstnameContainingIgnoreCase(firstname)
                .stream()
                .map(TerroristMapper::toDomain)
                .toList();
    }

    @QueryMapping
    public List<Terrorist> terroristsByWholename(@Argument String wholename){
        return repository.findByWholenameContainingIgnoreCase(wholename)
                .stream()
                .map(TerroristMapper::toDomain)
                .toList();
    }

    @QueryMapping
    public List<Terrorist> terroristsByNrn(@Argument String nrn){
        String normalized = NrnUtils.normalize(nrn); // remove all chars, only numbers
        return repository.searchByNormalizedNrn(normalized)
                .stream()
                .map(TerroristMapper::toDomain)
                .toList();
    }

}
