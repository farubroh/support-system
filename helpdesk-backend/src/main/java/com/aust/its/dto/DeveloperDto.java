package com.aust.its.dto;

public class DeveloperDto {

    private Long id;  // Developer ID
    private String username;  // Developer's username

    // Constructor
    public DeveloperDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}