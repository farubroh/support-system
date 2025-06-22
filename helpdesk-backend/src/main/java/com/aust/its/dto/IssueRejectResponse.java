package com.aust.its.dto;

import com.aust.its.enums.IssueStatus;
import lombok.Builder;

@Builder
public record IssueRejectResponse(
        String rejectedByName,
        String rejectedByRole,
        IssueStatus status
) { }
