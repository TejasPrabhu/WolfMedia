package com.tejasprabhu.wolfmedia.controller;

import com.tejasprabhu.wolfmedia.model.User;
import com.tejasprabhu.wolfmedia.service.UserService;
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
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Manage Users")
@Validated
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Fetch Users",
            description = "Get a list of users. Filter the users using query parameters if needed.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users")
    public List<User> findWithFilters(@Valid @RequestParam Map<String, Object> filters) {
        logger.info("Received request to fetch users with filters: {}", filters);
        return userService.findWithFilters(filters);
    }

    @PostMapping
    @Operation(summary = "Create a new User",
            description = "Create a new user.")
    @ApiResponse(responseCode = "201", description = "Successfully created a new user")
    public User createUser(@Valid @RequestBody User user) {
        logger.info("Received request to create user: {}", user);
        return userService.save(user);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a User",
            description = "Update an existing user.")
    @ApiResponse(responseCode = "200", description = "Successfully updated the user")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        if (user.getUserID() != id) {
            logger.warn("Mismatch between user ID in path and in request body");
            throw new IllegalArgumentException("Mismatch between user ID in path and in request body");
        }
        logger.info("Received request to update user with ID {}: {}", id, user);
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a User",
            description = "Delete an existing user.")
    @ApiResponse(responseCode = "200", description = "Successfully deleted the user")
    public void deleteUser(@PathVariable int id) {
        logger.info("Received request to delete user with ID: {}", id);
        userService.delete(id);
    }
}
