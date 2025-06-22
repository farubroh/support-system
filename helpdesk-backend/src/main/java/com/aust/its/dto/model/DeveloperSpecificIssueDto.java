package com.aust.its.dto.model;

import lombok.Builder;

@Builder
public record DeveloperSpecificIssueDto(
        UserDto createdBy,
        IssueDto issue
) { }
