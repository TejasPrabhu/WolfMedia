package com.tejasprabhu.wolfmedia.controller;

import com.tejasprabhu.wolfmedia.model.Podcast;
import com.tejasprabhu.wolfmedia.service.PodcastService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/podcasts")
@Tag(name = "Podcasts", description = "Manage Podcasts")
@Validated
public class PodcastController {

    private static final Logger logger = LoggerFactory.getLogger(PodcastController.class);

    @Autowired
    private PodcastService podcastService;

    @GetMapping
    @Operation(summary = "Fetch Podcasts",
            description = "Get a list of podcasts. Filter the podcasts using query parameters if needed.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of podcasts"),
            @ApiResponse(responseCode = "400", description = "Invalid query parameters provided")
    })
    public List<Podcast> findWithFilters(@Valid @RequestParam
                                         @Parameter(description = "Query parameters for filtering podcasts", example = "genre=comedy")
                                         Map<String, Object> filters) {
        logger.info("Received request to fetch podcasts with filters: {}", filters);
        return podcastService.findWithFilters(filters);
    }


    @PostMapping
    @Operation(summary = "Create Podcast",
            description = "Create a new podcast.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully created a new podcast"),
            @ApiResponse(responseCode = "400", description = "Invalid podcast data provided")
    })
    public Podcast createPodcast(@Valid @RequestBody
                                 @Parameter(description = "New Podcast object", required = true)
                                 Podcast podcast) {
        logger.info("Received request to create podcast: {}", podcast);
        return podcastService.save(podcast);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Podcast",
            description = "Update an existing podcast by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated the podcast"),
            @ApiResponse(responseCode = "400", description = "Invalid podcast data provided"),
            @ApiResponse(responseCode = "404", description = "Podcast not found")
    })
    public Podcast updatePodcast(@PathVariable
                                 @Parameter(description = "ID of the Podcast to be updated", required = true)
                                 int id,
                                 @Valid @RequestBody
                                 @Parameter(description = "Updated Podcast object", required = true)
                                 Podcast podcast) {
        if (podcast.getPodcastID() != id) {
            logger.warn("Mismatch between podcast ID in path and in request body");
            throw new IllegalArgumentException("Mismatch between podcast ID in path and in request body");
        }
        logger.info("Received request to update podcast with ID {}: {}", id, podcast);
        return podcastService.update(podcast);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Podcast",
            description = "Delete a podcast by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Successfully deleted the podcast"),
            @ApiResponse(responseCode = "404", description = "Podcast not found")
    })
    public void deletePodcast(@PathVariable
                              @Parameter(description = "ID of the Podcast to be deleted", required = true)
                              int id) {
        logger.info("Received request to delete podcast with ID: {}", id);
        podcastService.delete(id);
    }
}
