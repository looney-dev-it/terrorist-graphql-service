package com.ehb.javaadvanced.TerroristBE.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/********************
 * User Repository used for authentication mainly
 */

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}