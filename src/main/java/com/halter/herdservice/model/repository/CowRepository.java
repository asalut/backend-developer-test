package com.halter.herdservice.model.repository;

import com.halter.herdservice.model.Cow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CowRepository extends JpaRepository<Cow, UUID> {

    Optional<Cow> findByCowNumber(String cowNumber);
    Optional<Cow> findByCollarId(String collarId);
}
