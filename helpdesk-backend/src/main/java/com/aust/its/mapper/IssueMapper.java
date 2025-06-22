package com.aust.its.mapper;

import com.aust.its.dto.IssuePayload;
import com.aust.its.entity.Issue;
import com.aust.its.entity.User;
import com.aust.its.enums.IssueStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class IssueMapper {

    public static Issue payloadToEntity(final IssuePayload payload, final User user) {

        return
                Issue
                        .builder()
                        .title(payload.title())
                        .description(payload.description())
                        .user(user)
                        .status(IssueStatus.PENDING)
                        .createdAt(LocalDateTime.now())
                        .serialId(UUID.randomUUID().toString())
                        .build();
    }
}
