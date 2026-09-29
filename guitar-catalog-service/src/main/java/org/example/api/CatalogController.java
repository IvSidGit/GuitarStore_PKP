package org.example.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import org.example.service.GuitarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CatalogController {
    private final GuitarService guitarService;

    public CatalogController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @GetMapping("/brands")
    List<BrandResponse> brands() {
        return guitarService.brands();
    }

    @PostMapping("/brands")
    @ResponseStatus(HttpStatus.CREATED)
    BrandResponse createBrand(@Valid @RequestBody CreateBrandRequest request) {
        return guitarService.createBrand(request);
    }

    @GetMapping("/guitars")
    List<GuitarResponse> guitars() {
        return guitarService.guitars();
    }

    @GetMapping("/guitars/{id}")
    GuitarResponse guitar(@PathVariable UUID id) {
        return guitarService.guitar(id);
    }

    @PostMapping("/guitars")
    @ResponseStatus(HttpStatus.CREATED)
    GuitarResponse createGuitar(@Valid @RequestBody CreateGuitarRequest request) {
        return guitarService.createGuitar(request);
    }
}