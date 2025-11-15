package com.mundomagico.api.repository;

import com.mundomagico.api.model.Brinquedo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {
    // Spring Data JPA fornece automaticamente:
    // .save(Brinquedo)
    // .findAll()
    // .findById(Long)
    // entre outros.
}