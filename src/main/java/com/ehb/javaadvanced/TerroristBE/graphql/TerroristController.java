package com.ehb.javaadvanced.TerroristBE.graphql;

import com.ehb.javaadvanced.TerroristBE.domain.Terrorist;
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
    public List<Terrorist> terroristsByLastName(@Argument String lastname){
        return repository.findByLastname(lastname)
                .stream()
                .map(TerroristMapper::toDomain)
                .toList();
    }
}
