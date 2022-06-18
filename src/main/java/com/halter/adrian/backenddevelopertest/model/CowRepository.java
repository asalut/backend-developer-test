package com.halter.adrian.backenddevelopertest.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CowRepository extends JpaRepository<Cow, UUID> {

    Optional<Cow> findByCowNumber(Long cowNumber);
}
