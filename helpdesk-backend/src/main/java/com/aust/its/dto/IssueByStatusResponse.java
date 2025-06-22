package com.aust.its.dto;

import com.aust.its.entity.User;
import com.aust.its.enums.IssueStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class IssueByStatusResponse {
    long id;
    String title;
    String description;
    User user;
    IssueStatus status;
    LocalDateTime createdAt;
    LocalDateTime completedAt;
    LocalDateTime rejectedAt;
    String serialId;
    String developerName;
    String completedReason;
    String rejectedReason;
}
