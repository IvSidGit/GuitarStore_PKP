package org.example.repository;

import org.example.domain.GuitarEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GuitarRepository extends JpaRepository<GuitarEntity, UUID> {
    boolean existsBySerialNumberAndBrandId(String serialNumber, UUID brandId);

    @Override
    @EntityGraph(attributePaths = "brand")
    List<GuitarEntity> findAll();

    @Override
    @EntityGraph(attributePaths = "brand")
    Optional<GuitarEntity> findById(UUID id);
}

