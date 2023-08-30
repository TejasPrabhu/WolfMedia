package com.tejasprabhu.wolfmedia.controller;

import com.tejasprabhu.wolfmedia.model.Album;
import com.tejasprabhu.wolfmedia.service.AlbumService;
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
@RequestMapping("/api/albums")
@Tag(name = "Albums", description = "Manage Albums")
@Validated
public class AlbumController {
    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    private AlbumService albumService;

    @GetMapping
    @Operation(summary = "Fetch Albums", description = "Get a list of all albums. Filter the albums using query parameters if needed.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful retrieval of albums"),
            @ApiResponse(responseCode = "400", description = "Invalid query parameters provided")
    })
    public List<Album> findWithFilters(@Valid @RequestParam
                                       @Parameter(
                                               description = "Query parameters for filtering albums. E.g: 'genre=pop', 'artist=xyz'",
                                               example = "genre=pop"
                                       )
                                       Map<String, Object> filters) {
        logger.info("Received request to fetch albums with filters: {}", filters);
        return albumService.findWithFilters(filters);
    }

    @PostMapping
    @Operation(summary = "Create Album", description = "Create a new album.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Album successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid album data provided")
    })
    public Album createAlbum(@Valid @RequestBody
                             @Parameter(
                                     description = "New Album object",
                                     required = true
                             )
                             Album newAlbum) {
        logger.info("Received request to create new album: {}", newAlbum);
        return albumService.save(newAlbum);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Album", description = "Update an existing album by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Album successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid album data provided"),
            @ApiResponse(responseCode = "404", description = "Album not found")
    })
    public Album updateAlbum(@PathVariable int id,
                             @Valid @RequestBody
                             @Parameter(
                                     description = "Updated Album object",
                                     required = true
                             )
                             Album updatedAlbum) {
        logger.info("Received request to update album with ID {}: {}", id, updatedAlbum);
        return albumService.update(updatedAlbum);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Album", description = "Delete an album by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Album successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Album not found")
    })
    public void deleteAlbum(@PathVariable
                            @Parameter(
                                    description = "ID of the Album to be deleted",
                                    required = true
                            )
                            int id) {
        logger.info("Received request to delete album with ID: {}", id);
        albumService.delete(id);
    }
}
