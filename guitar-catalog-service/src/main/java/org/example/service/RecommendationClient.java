package org.example.service;

import org.example.api.RecommendationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;

import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;
import static org.springframework.security.oauth2.client.web.client.RequestAttributePrincipalResolver.principal;

@Service
@Profile("!session-auth")
public class RecommendationClient {
    private static final Logger log = LoggerFactory.getLogger(RecommendationClient.class);
    private final RestClient recommendationRestClient;

    public RecommendationClient(RestClient recommendationRestClient) {
        this.recommendationRestClient = recommendationRestClient;
    }

    public RecommendationResponse recommend(UUID guitarId) {
        log.info("Calling recommendation-service guitarId={}", guitarId);
        return recommendationRestClient.get()
                .uri("/internal/recommendations/{guitarId}", guitarId)
                .attributes(clientRegistrationId("recommendation"))
                .attributes(principal("guitar-catalog-service"))
                .retrieve()
                .body(RecommendationResponse.class);
    }
}

