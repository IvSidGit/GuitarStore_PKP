package org.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.util.UUID;

@Entity
@Table(name = "brands")
public class BrandEntity {
    @Id
    private UUID id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(name = "founded_year")
    private Integer foundedYear;

    @Version
    private long version;

    protected BrandEntity() {
    }

    public BrandEntity(UUID id, String name, String description, Integer foundedYear) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.foundedYear = foundedYear;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getFoundedYear() {
        return foundedYear;
    }

    public long getVersion() {
        return version;
    }
}
