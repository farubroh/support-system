package com.aust.its.dto;

import com.aust.its.enums.IssueStatus;

public record IssueStatusUpdatePayload(
        Long workedBy,
        IssueStatus fromStatus,
        IssueStatus toStatus,
        String rejectionReason,
        String completedAnalysis
) { }
