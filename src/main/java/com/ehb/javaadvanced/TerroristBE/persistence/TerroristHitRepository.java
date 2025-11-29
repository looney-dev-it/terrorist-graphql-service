package com.ehb.javaadvanced.TerroristBE.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/******************
 * Basic repository to interact between Entity & Database - used mainly to add/retrieve
 */

public interface TerroristHitRepository extends JpaRepository<TerroristHitEntity, String> {

}
