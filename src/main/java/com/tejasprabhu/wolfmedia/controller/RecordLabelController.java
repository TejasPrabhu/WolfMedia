package com.tejasprabhu.wolfmedia.controller;

import com.tejasprabhu.wolfmedia.model.RecordLabel;
import com.tejasprabhu.wolfmedia.service.RecordLabelService;
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
@RequestMapping("/api/recordlabels")
@Tag(name = "Record Labels", description = "Manage Record Labels")
@Validated
public class RecordLabelController {

    private static final Logger logger = LoggerFactory.getLogger(RecordLabelController.class);

    @Autowired
    private RecordLabelService recordLabelService;

    @GetMapping
    @Operation(summary = "Fetch Record Labels",
            description = "Get a list of record labels. Filter the labels using query parameters if needed.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of record labels")
    public List<RecordLabel> findWithFilters(@Valid @RequestParam Map<String, Object> filters) {
        logger.info("Received request to fetch record labels with filters: {}", filters);
        return recordLabelService.findWithFilters(filters);
    }

    @PostMapping
    @Operation(summary = "Create a new Record Label",
            description = "Create a new record label.")
    @ApiResponse(responseCode = "201", description = "Successfully created a new record label")
    public RecordLabel createRecordLabel(@Valid @RequestBody RecordLabel recordLabel) {
        logger.info("Received request to create record label: {}", recordLabel);
        return recordLabelService.save(recordLabel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Record Label",
            description = "Update an existing record label.")
    @ApiResponse(responseCode = "200", description = "Successfully updated the record label")
    public RecordLabel updateRecordLabel(@PathVariable int id, @RequestBody RecordLabel recordLabel) {
        if (recordLabel.getLabelID() != id) {
            logger.warn("Mismatch between record label ID in path and in request body");
            throw new IllegalArgumentException("Mismatch between record label ID in path and in request body");
        }
        logger.info("Received request to update record label with ID {}: {}", id, recordLabel);
        return recordLabelService.update(recordLabel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Record Label",
            description = "Delete an existing record label.")
    @ApiResponse(responseCode = "200", description = "Successfully deleted the record label")
    public void deleteRecordLabel(@PathVariable int id) {
        logger.info("Received request to delete record label with ID: {}", id);
        recordLabelService.delete(id);
    }
}
