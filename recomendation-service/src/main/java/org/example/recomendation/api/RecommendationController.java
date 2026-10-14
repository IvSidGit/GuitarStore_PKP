package org.example.recomendation.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/internal")
public class RecommendationController {
    @GetMapping("/recommendations/{guitarId}")
    @PreAuthorize("hasRole('SERVICE')")
    public RecommendationResponse recommendations(@PathVariable UUID guitarId) {
        return new RecommendationResponse(guitarId, List.of("Clean Code", "Refactoring"));
    }

    @GetMapping("/admin/info")
    @PreAuthorize("hasRole('OPERATOR')")
    public Map<String, String> info() {
        return Map.of("service", "recommendation-service", "status", "ok");
    }

    public record RecommendationResponse(UUID guitarId, List<String> titles) {}
}
