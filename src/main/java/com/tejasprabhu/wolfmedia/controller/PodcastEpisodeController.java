package com.tejasprabhu.wolfmedia.controller;

import com.tejasprabhu.wolfmedia.model.PodcastEpisode;
import com.tejasprabhu.wolfmedia.service.PodcastEpisodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/podcast-episodes")
@Tag(name = "Podcast Episodes", description = "Manage Podcast Episodes")
@Validated
public class PodcastEpisodeController {

    private static final Logger logger = LoggerFactory.getLogger(PodcastEpisodeController.class);

    @Autowired
    private PodcastEpisodeService podcastEpisodeService;

    @GetMapping
    @Operation(summary = "Fetch Podcast Episodes",
            description = "Get a list of podcast episodes. Filter the episodes using query parameters if needed.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of podcast episodes")
    public List<PodcastEpisode> findWithFilters(@Valid @RequestParam Map<String, Object> filters) {
        logger.info("Received request to fetch podcast episodes with filters: {}", filters);
        return podcastEpisodeService.findWithFilters(filters);
    }

    @PostMapping
    @Operation(summary = "Create a new Podcast Episode",
            description = "Create a new podcast episode.")
    @ApiResponse(responseCode = "201", description = "Successfully created a new podcast episode")
    public PodcastEpisode createPodcastEpisode(@Valid @RequestBody PodcastEpisode podcastEpisode) {
        logger.info("Received request to create podcast episode: {}", podcastEpisode);
        return podcastEpisodeService.save(podcastEpisode);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Podcast Episode",
            description = "Update an existing podcast episode.")
    @ApiResponse(responseCode = "200", description = "Successfully updated the podcast episode")
    public PodcastEpisode updatePodcastEpisode(@PathVariable int id, @RequestBody PodcastEpisode podcastEpisode) {
        if (podcastEpisode.getEpisodeID() != id) {
            logger.warn("Mismatch between podcast episode ID in path and in request body");
            throw new IllegalArgumentException("Mismatch between podcast episode ID in path and in request body");
        }
        logger.info("Received request to update podcast episode with ID {}: {}", id, podcastEpisode);
        return podcastEpisodeService.update(podcastEpisode);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Podcast Episode",
            description = "Delete an existing podcast episode.")
    @ApiResponse(responseCode = "200", description = "Successfully deleted the podcast episode")
    public void deletePodcastEpisode(@PathVariable int id) {
        logger.info("Received request to delete podcast episode with ID: {}", id);
        podcastEpisodeService.delete(id);
    }
}
