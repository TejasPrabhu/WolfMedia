package com.tejasprabhu.wolfmedia.controller;

import com.tejasprabhu.wolfmedia.model.PodcastHost;
import com.tejasprabhu.wolfmedia.service.PodcastHostService;
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
@RequestMapping("/api/podcast-hosts")
@Tag(name = "Podcast Hosts", description = "Manage Podcast Hosts")
@Validated
public class PodcastHostController {

    private static final Logger logger = LoggerFactory.getLogger(PodcastHostController.class);

    @Autowired
    private PodcastHostService podcastHostService;

    @GetMapping
    @Operation(summary = "Fetch Podcast Hosts",
            description = "Get a list of podcast hosts. Filter the hosts using query parameters if needed.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of podcast hosts")
    public List<PodcastHost> findWithFilters(@Valid @RequestParam Map<String, Object> filters) {
        logger.info("Received request to fetch podcast hosts with filters: {}", filters);
        return podcastHostService.findWithFilters(filters);
    }

    @PostMapping
    @Operation(summary = "Create a new Podcast Host",
            description = "Create a new podcast host.")
    @ApiResponse(responseCode = "201", description = "Successfully created a new podcast host")
    public PodcastHost createPodcastHost(@Valid @RequestBody PodcastHost podcastHost) {
        logger.info("Received request to create podcast host: {}", podcastHost);
        return podcastHostService.save(podcastHost);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Podcast Host",
            description = "Update an existing podcast host.")
    @ApiResponse(responseCode = "200", description = "Successfully updated the podcast host")
    public PodcastHost updatePodcastHost(@PathVariable int id, @RequestBody PodcastHost podcastHost) {
        if (podcastHost.getHostID() != id) {
            logger.warn("Mismatch between podcast host ID in path and in request body");
            throw new IllegalArgumentException("Mismatch between podcast host ID in path and in request body");
        }
        logger.info("Received request to update podcast host with ID {}: {}", id, podcastHost);
        return podcastHostService.update(podcastHost);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Podcast Host",
            description = "Delete an existing podcast host.")
    @ApiResponse(responseCode = "200", description = "Successfully deleted the podcast host")
    public void deletePodcastHost(@PathVariable int id) {
        logger.info("Received request to delete podcast host with ID: {}", id);
        podcastHostService.delete(id);
    }
}
