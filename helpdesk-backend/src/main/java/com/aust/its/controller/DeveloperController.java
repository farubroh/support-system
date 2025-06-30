package com.aust.its.controller;

import com.aust.its.dto.IssuesOfDeveloperDto;
import com.aust.its.entity.Developer;
import com.aust.its.service.DeveloperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/developers")
@RequiredArgsConstructor
@Tag(name = "Developer Controller", description = "APIs for developer feature")
public class DeveloperController {

    private static final Logger logger = LoggerFactory.getLogger(DeveloperController.class);

    private final DeveloperService developerService;

    @Operation(summary = "To show all the developers", description = "View all developers info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Developer info successfully found",
                    content = @Content(schema = @Schema(implementation = Developer.class))),
            @ApiResponse(responseCode = "204", description = "Developer info empty",
                    content = @Content(schema = @Schema(implementation = Developer.class))),
            @ApiResponse(responseCode = "500", description = "Server Error",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping
    public List<Developer> getDevelopers() {
        return developerService.getAll();
    }

    @GetMapping("{id}/issues")
    public IssuesOfDeveloperDto getIssuesOfDeveloper(@PathVariable("id") Long userId) {
        Developer developer = developerService.getByUserId(userId);
        logger.info("Finding issues of developer : {}", developer.getId());

        return developerService.getIssuesOfDeveloper(developer.getId());
    }
}
