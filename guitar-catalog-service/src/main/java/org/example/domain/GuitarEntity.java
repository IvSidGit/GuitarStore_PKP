package org.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.util.UUID;

@Entity
@Table(name = "guitars")
public class GuitarEntity {
    @Id
    private UUID id;

    @Column(nullable = false, length = 10)
    private String serialNumber;

    @Column(nullable = false, length = 30)
    private String model;

    @Column(nullable = false, length = 9)
    private String type;

    @Column(length = 20)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandEntity brand;

    @Column(nullable = false)
    private Integer price;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Version
    private long version;

    protected GuitarEntity() {
    }

    public GuitarEntity(UUID id, String serialNumber, String model, String type, String color, BrandEntity brand, Integer price, Integer publicationYear) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.model = model;
        this.type = type;
        this.color = color;
        this.brand = brand;
        this.price = price;
        this.publicationYear = publicationYear;
    }

    public UUID getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public long getVersion() {
        return version;
    }
}
