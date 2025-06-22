package com.aust.its.dto;

import com.aust.its.dto.model.DeveloperSpecificIssueDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record IssuesOfDeveloperDto(
        @JsonProperty("PENDING")
        List<DeveloperSpecificIssueDto> pendingIssues,

        @JsonProperty("INPROGRESS")
        List<DeveloperSpecificIssueDto> ongoingIssues,

        @JsonProperty("COMPLETED")
        List<DeveloperSpecificIssueDto> completedIssues,

        @JsonProperty("REJECTED")
        List<DeveloperSpecificIssueDto> rejectedIssues
) { }
