package com.aust.its.enums;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("Admin"),
    DEVELOPER("Developer"),
    TEACHER("Teacher"),
    STUDENT("Student");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}
