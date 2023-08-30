package com.tejasprabhu.wolfmedia.controller;

import com.tejasprabhu.wolfmedia.model.Artist;
import com.tejasprabhu.wolfmedia.service.ArtistService;
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
@RequestMapping("/api/artists")
@Tag(name = "Artists", description = "Manage Artists")
@Validated
public class ArtistController {
    private static final Logger logger = LoggerFactory.getLogger(ArtistController.class);

    @Autowired
    private ArtistService artistService;

    @GetMapping
    @Operation(summary = "Fetch Artists", description = "Get a list of all artists. Filter the artists using query parameters if needed.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful retrieval of artists"),
            @ApiResponse(responseCode = "400", description = "Invalid query parameters provided")
    })
    public List<Artist> findWithFilters(@Valid @RequestParam
                                        @Parameter(
                                                description = "Query parameters for filtering artists. E.g: 'genre=pop', 'country=UK'",
                                                example = "genre=pop"
                                        )
                                        Map<String, Object> filters) {
        logger.info("Received request to fetch artists with filters: {}", filters);
        return artistService.findWithFilters(filters);
    }

    @PostMapping
    @Operation(summary = "Create Artist", description = "Create a new artist.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Artist successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid artist data provided")
    })
    public Artist createArtist(@Valid @RequestBody
                               @Parameter(
                                       description = "New Artist object",
                                       required = true
                               )
                               Artist newArtist) {
        logger.info("Received request to create new artist: {}", newArtist);
        return artistService.save(newArtist);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Artist", description = "Update an existing artist by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Artist successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid artist data provided"),
            @ApiResponse(responseCode = "404", description = "Artist not found")
    })
    public Artist updateArtist(@PathVariable int id,
                               @Valid @RequestBody
                               @Parameter(
                                       description = "Updated Artist object",
                                       required = true
                               )
                               Artist updatedArtist) {
        logger.info("Received request to update artist with ID {}: {}", id, updatedArtist);
        return artistService.update(updatedArtist);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Artist", description = "Delete an artist by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Artist successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Artist not found")
    })
    public void deleteArtist(@PathVariable
                             @Parameter(
                                     description = "ID of the Artist to be deleted",
                                     required = true
                             )
                             int id) {
        logger.info("Received request to delete artist with ID: {}", id);
        artistService.delete(id);
    }
}
