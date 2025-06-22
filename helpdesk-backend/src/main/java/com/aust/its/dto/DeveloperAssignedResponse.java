package com.aust.its.dto;

import lombok.Builder;

@Builder
public record DeveloperAssignedResponse(
        String developerName,
        long currentlyTotalTaskInHand
) { }
