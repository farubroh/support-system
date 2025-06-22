package com.aust.its.dto;

public record IssueRejectPayload(
        String rejectedByRole,
        long rejectedById,
        String rejectionReason
) { }
