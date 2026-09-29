package org.example.api;

import java.util.UUID;

public record GuitarResponse(
        UUID id,
        String serialNumber,
        String model,
        String type,
        String color,
        UUID brandId,
        String brandName,
        Integer price,
        Integer publicationYear
) {
}
