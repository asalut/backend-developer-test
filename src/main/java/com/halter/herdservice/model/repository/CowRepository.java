package com.halter.herdservice.model.repository;

import com.halter.herdservice.model.Cow;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CowRepository extends JpaRepository<Cow, UUID> {

    Optional<Cow> findByCowNumber(String cowNumber);
    Optional<Cow> findByCollarId(String collarId);

}
