package org.example.repository;

import org.example.domain.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BrandRepository extends JpaRepository<BrandEntity, UUID> {
    boolean existsByName(String name);
}

