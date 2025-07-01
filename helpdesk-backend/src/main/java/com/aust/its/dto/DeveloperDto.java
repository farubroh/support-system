package com.aust.its.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeveloperDto {

    // Getters and Setters
    private Long id;  // Developer ID
    private String username;  // Developer's username

    // Constructor
    public DeveloperDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

}