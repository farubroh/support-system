package com.aust.its.entity;

import com.aust.its.enums.IssueStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private IssueStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private LocalDateTime rejectedAt;
    private String serialId; // it will assigned by admin when the issue is assigned to developer

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_to_id")
    @JsonBackReference
    @JsonIgnoreProperties({"assignedIssues", "resolvedIssues", "rejectedIssues"})
    private Developer assignedTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resolved_by_id")
    @JsonBackReference
    @JsonIgnoreProperties({"assignedIssues", "resolvedIssues", "rejectedIssues"})
    private Developer resolvedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rejected_by_id")
    @JsonBackReference
    @JsonIgnoreProperties({"assignedIssues", "resolvedIssues", "rejectedIssues"})
    private Developer rejectedBy;

    private String rejectedByAdmin;
    private String rejectionReason;
    private String completedReason;
    // ✅ New field to store uploaded file paths
    @ElementCollection
    @CollectionTable(name = "issue_files", joinColumns = @JoinColumn(name = "issue_id"))
    @Column(name = "file_path")
    private List<String> filePaths;
}
