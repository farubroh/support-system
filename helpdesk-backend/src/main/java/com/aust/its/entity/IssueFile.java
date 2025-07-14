package com.aust.its.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "issue_files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class IssueFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Required
    private Long id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
}