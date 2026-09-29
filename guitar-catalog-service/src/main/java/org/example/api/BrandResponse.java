package org.example.api;

import java.util.UUID;

public record BrandResponse(UUID id, String name, String description, Integer foundedYear) {
}