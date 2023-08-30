package com.tejasprabhu.wolfmedia.controller;

import com.tejasprabhu.wolfmedia.model.Song;
import com.tejasprabhu.wolfmedia.service.SongService;
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
@RequestMapping("/api/songs")
@Tag(name = "Songs", description = "Manage Songs")
@Validated
public class SongController {

    private static final Logger logger = LoggerFactory.getLogger(SongController.class);

    @Autowired
    private SongService songService;

    @GetMapping
    @Operation(summary = "Fetch Songs",
            description = "Get a list of songs. Filter the songs using query parameters if needed.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of songs")
    public List<Song> findWithFilters(@Valid @RequestParam Map<String, Object> filters) {
        logger.info("Received request to fetch songs with filters: {}", filters);
        return songService.findWithFilters(filters);
    }

    @PostMapping
    @Operation(summary = "Create a new Song",
            description = "Create a new song.")
    @ApiResponse(responseCode = "201", description = "Successfully created a new song")
    public Song createSong(@Valid @RequestBody Song song) {
        logger.info("Received request to create song: {}", song);
        return songService.save(song);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Song",
            description = "Update an existing song.")
    @ApiResponse(responseCode = "200", description = "Successfully updated the song")
    public Song updateSong(@PathVariable int id, @RequestBody Song song) {
        if (song.getSongID() != id) {
            logger.warn("Mismatch between song ID in path and in request body");
            throw new IllegalArgumentException("Mismatch between song ID in path and in request body");
        }
        logger.info("Received request to update song with ID {}: {}", id, song);
        return songService.update(song);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Song",
            description = "Delete an existing song.")
    @ApiResponse(responseCode = "200", description = "Successfully deleted the song")
    public void deleteSong(@PathVariable int id) {
        logger.info("Received request to delete song with ID: {}", id);
        songService.delete(id);
    }
}
