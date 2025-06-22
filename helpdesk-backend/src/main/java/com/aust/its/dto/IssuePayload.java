package com.aust.its.dto;

public record IssuePayload(
    String description,
    String title,
    long userId
) { }
