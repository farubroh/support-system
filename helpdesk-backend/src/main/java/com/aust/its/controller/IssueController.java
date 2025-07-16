package com.aust.its.controller;

import com.aust.its.dto.*;
import com.aust.its.entity.Developer;
import com.aust.its.entity.Issue;
import com.aust.its.entity.IssueFile;
import com.aust.its.entity.User;
import com.aust.its.enums.IssueStatus;
import com.aust.its.mapper.IssueMapper;
import com.aust.its.repository.IssueRepository;
import com.aust.its.service.IssueService;
import com.aust.its.service.UserService;
//import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.aust.its.service.DeveloperService;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {

    private static final Logger logger = LoggerFactory.getLogger(IssueController.class);

    private final IssueRepository repository;
    private final IssueService issueService;
    private final UserService userService;

    @PostMapping
    public Issue submitIssue(@RequestBody IssuePayload issuePayload) {
        logger.info("Issue Payload :: {}", issuePayload);

        User user = userService.getById(issuePayload.userId());
        Issue issue = IssueMapper.payloadToEntity(issuePayload, user);
        return repository.save(issue);
    }
    @GetMapping("/all_admin")
    public List<IssueByStatusResponse> getAllIssuesForAdmin() {
        return issueService.getAllIssuesForKPI();
    }


//    @GetMapping("/user/{id}")
//    public List<Issue> getIssues(@PathVariable("id") Long userId,
//                                 @RequestParam IssueStatus status) {
//
//        logger.info("finding issues of userId :: {} for status :: {}", userId, status);
//        return issueService.getIssuesByUserIdAndStatus(userId, status);
//    }

    @GetMapping("/user/{id}")
    public List<IssueByStatusResponse> getIssues(@PathVariable("id") Long userId,
                                                 @RequestParam IssueStatus status) {
        logger.info("finding issues of userId :: {} for status :: {}", userId, status);
        return issueService.getIssueResponsesByUserIdAndStatus(userId, status);
    }



    @GetMapping("/status/{status}")
    public List<IssueByStatusResponse> getIssuesByStatus(@PathVariable("status") IssueStatus status) {
        logger.info("finding issues of status :: {}", status);
        return issueService.getIssuesByStatus(status);
    }
    @GetMapping("/all")
    public List<Issue> getAllIssues() {
        return repository.findAll();
    }




    // Endpoint to assign issue to a developer
    @PostMapping("{id}/assign")
    public DeveloperAssignedResponse assignIssueToDeveloper(@PathVariable Long id,
                                                            @RequestBody IssueAssignPayload issueAssignPayload) {
        return issueService.assignIssue(id, issueAssignPayload);
    }


    @PostMapping("{id}/reject")
    public IssueRejectResponse rejectIssue(@PathVariable Long id,
                                           @RequestBody IssueRejectPayload issueRejectPayload) {
        return issueService.rejectIssue(id, issueRejectPayload);
    }

    @PutMapping("{id}/status")
    public Issue updateStatus(@PathVariable Long id,
                              @RequestBody IssueStatusUpdatePayload issueStatusUpdatePayload) {
        return issueService.updateIssueByStatus(id, issueStatusUpdatePayload);
    }

    @PutMapping("{id}/assign")
    public Issue updateAssignee(@PathVariable("id") Long issueId,
                                @RequestBody AssignDeveloperPayload assignDeveloperPayload) {
        return issueService.updateAssignee(issueId, assignDeveloperPayload.developerId());
    }

    @PostMapping("/with-files")
    public ResponseEntity<?> createIssueWithFiles(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("userId") Long userId,
            @RequestParam("category") String category,
            @RequestParam(value = "files", required = false) List<MultipartFile> files
    ) {
        try {
            List<String> savedFileNames = new ArrayList<>();

            if (files != null && !files.isEmpty()) {
                for (MultipartFile file : files) {
                    String originalFilename = file.getOriginalFilename();
                    if (originalFilename == null || originalFilename.isBlank()) continue;

                    // ✅ Fixed File Save Location
                    String uploadDir = "D:/iums_images/" + userId;
                    File dir = new File(uploadDir);
                    if (!dir.exists()) dir.mkdirs();

                    File dest = new File(uploadDir + "/" + originalFilename);
                    file.transferTo(dest);

                    savedFileNames.add(originalFilename); // ✅ Save only filename
                }
            }

            Issue newIssue = issueService.createIssueWithFiles(
                    title, description, userId, category, savedFileNames
            );

            return ResponseEntity.ok(newIssue);

        } catch (Exception e) {
            e.printStackTrace(); // helpful for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving issue: " + e.getMessage());
        }
    }


    @GetMapping("/files/{userId}/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String userId, @PathVariable String filename) {
        try {
            Path path = Paths.get("D:/iums_images/" + userId).resolve(filename);
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // 🔍 Detect the MIME type
            String mimeType = Files.probeContentType(path);
            if (mimeType == null) {
                mimeType = "application/octet-stream"; // Fallback
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mimeType))
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}