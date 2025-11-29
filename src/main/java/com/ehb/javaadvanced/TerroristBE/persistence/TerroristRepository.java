package com.ehb.javaadvanced.TerroristBE.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TerroristRepository extends JpaRepository<TerroristEntity, String> {
    List<TerroristEntity> findByLastnameContainingIgnoreCase(String fragment);
    List<TerroristEntity> findByFirstnameContainingIgnoreCase(String fragment);
    List<TerroristEntity> findByWholenameContainingIgnoreCase(String fragment);

    @Query("SELECT t FROM TerroristEntity t " +
            "WHERE REPLACE(REPLACE(REPLACE(LOWER(t.nrn), 'nrn', ''), '-', ''), '.', '') " +
            "LIKE LOWER(CONCAT('%', :nrn, '%'))")
    List<TerroristEntity> findByNormalizedNrn(@Param("nrn") String nrn);
}
