package com.aust.its.controller;

import com.aust.its.dto.*;
import com.aust.its.entity.Issue;
import com.aust.its.entity.User;
import com.aust.its.enums.IssueStatus;
import com.aust.its.mapper.IssueMapper;
import com.aust.its.repository.IssueRepository;
import com.aust.its.service.IssueService;
import com.aust.its.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/user/{id}")
    public List<Issue> getIssues(@PathVariable("id") Long userId,
                                 @RequestParam IssueStatus status) {

        logger.info("finding issues of userId :: {} for status :: {}", userId, status);
        return issueService.getIssuesByUserIdAndStatus(userId, status);
    }

    @GetMapping("/status/{status}")
    public List<IssueByStatusResponse> getIssuesByStatus(@PathVariable("status") IssueStatus status) {
        logger.info("finding issues of status :: {}", status);
        return issueService.getIssuesByStatus(status);
    }

    @PostMapping("{id}/assign")
    public DeveloperAssignedResponse assignIssueToDeveloper(@PathVariable Long id,
                                                            @RequestBody IssueAssignPayload issueAssignPayload) {
        logger.info("Assigning issue {} to developer", id);
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
}