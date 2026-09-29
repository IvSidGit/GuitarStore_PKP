package org.example.service;

import org.example.api.*;
import org.example.domain.BrandEntity;
import org.example.domain.GuitarEntity;
import org.example.repository.BrandRepository;
import org.example.repository.GuitarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class GuitarService {
    private final BrandRepository brandRepository;
    private final GuitarRepository guitarRepository;

    public GuitarService(BrandRepository brandRepository, GuitarRepository guitarRepository) {
        this.brandRepository = brandRepository;
        this.guitarRepository = guitarRepository;
    }

    @Transactional
    public BrandResponse createBrand(CreateBrandRequest request) {
        if (brandRepository.existsByName(request.name())) {
            throw new ApiException(HttpStatus.CONFLICT, "Бренд с таким именем уже существует");
        }
        BrandEntity brand = brandRepository.save(
                new BrandEntity(UUID.randomUUID(), request.name().trim(), request.description(), request.foundedYear())
        );
        return new BrandResponse(brand.getId(), brand.getName(), brand.getDescription(), brand.getFoundedYear());
    }

    @Transactional(readOnly = true)
    public List<BrandResponse> brands() {
        return brandRepository.findAll().stream()
                .map(brand -> new BrandResponse(brand.getId(), brand.getName(), brand.getDescription(), brand.getFoundedYear()))
                .toList();
    }

    @Transactional
    public GuitarResponse createGuitar(CreateGuitarRequest request) {
        if (guitarRepository.existsBySerialNumberAndBrandId(request.serialNumber(),request.brandId())) {
            throw new ApiException(HttpStatus.CONFLICT, "Гитара с таким серийным номером у этого бренда уже существует");
        }
        BrandEntity brand = brandRepository.findById(request.brandId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Бренд не найден"));
        GuitarEntity guitar = guitarRepository.save(new GuitarEntity(
                UUID.randomUUID(),
                request.serialNumber(),
                request.model().trim(),
                request.type(),
                request.color(),
                brand,
                request.price(),
                request.publicationYear()
        ));
        return toResponse(guitar);
    }

    @Transactional(readOnly = true)
    public List<GuitarResponse> guitars() {
        return guitarRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public GuitarResponse guitar(UUID id) {
        GuitarEntity guitar = guitarRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Гитара не найдена"));
        return toResponse(guitar);
    }

    private GuitarResponse toResponse(GuitarEntity guitar) {
        return new GuitarResponse(
                guitar.getId(),
                guitar.getSerialNumber(),
                guitar.getModel(),
                guitar.getType(),
                guitar.getColor(),
                guitar.getBrand().getId(),
                guitar.getBrand().getName(),
                guitar.getPrice(),
                guitar.getPublicationYear()
        );
    }
}
