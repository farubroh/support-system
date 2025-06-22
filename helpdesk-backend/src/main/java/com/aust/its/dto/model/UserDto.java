package com.aust.its.dto.model;

import lombok.Builder;

@Builder
public record UserDto(
        String username,
        String role,
        String department,
        String designation
) { }
