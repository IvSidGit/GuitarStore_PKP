package org.example.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.validation.ValidYearBrand;

public record CreateBrandRequest(
        @NotBlank @Size(max = 200) String name,
        @NotBlank @Size(max = 1000) String description,
        @ValidYearBrand Integer foundedYear
) {
}
