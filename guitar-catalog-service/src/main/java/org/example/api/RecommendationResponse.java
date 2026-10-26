package org.example.api;

import java.util.List;
import java.util.UUID;

public record RecommendationResponse(UUID guitarId, List<String> models) {}

