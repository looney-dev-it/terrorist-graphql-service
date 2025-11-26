package com.ehb.javaadvanced.TerroristBE.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerroristRepository extends JpaRepository<TerroristEntity, String> {
    List<TerroristEntity> findByLastname(String lastname);
}
