package org.example.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.example.validation.*;

import java.util.UUID;

public record CreateGuitarRequest(
        @NotBlank @Size(max = 10) String serialNumber,
        @NotBlank @Size(max = 30) String model,
        @NotBlank @Size(max = 9) @ValidGuitarType String type,
        @Size(max = 20) String color,
        @NotNull UUID brandId,
        @NotBlank @Min(5000) @Max(200_000) Integer price,
        @ValidYearGutar Integer publicationYear
) {
}

