package com.aust.its.dto.model;

import com.aust.its.enums.IssueStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record IssueDto(
        long issueId,
        String title,
        String description,
        IssueStatus status,
        String serialId,
        LocalDateTime createdAt,
        LocalDateTime completedAt,
        LocalDateTime rejectedAt,
        String completedReason,
        String rejectionReason
) { }
