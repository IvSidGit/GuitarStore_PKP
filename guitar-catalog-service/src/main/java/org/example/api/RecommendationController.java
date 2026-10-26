package org.example.api;

import org.example.service.RecommendationClient;
import org.example.service.GuitarService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Profile("!session-auth")
@RequestMapping("/api/guitars")
public class RecommendationController {
    private final GuitarService guitars;
    private final RecommendationClient recommendations;

    public RecommendationController(GuitarService guitars, RecommendationClient recommendations) {
        this.guitars = guitars;
        this.recommendations = recommendations;
    }

    @GetMapping("/{id}/recommendation")
    @PreAuthorize("hasAnyRole('READER','EDITOR')")
    public RecommendationResponse recommendation(@PathVariable UUID id) {
        guitars.guitar(id);
        return recommendations.recommend(id);
    }
}

