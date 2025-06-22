package com.aust.its.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "developers")
//@Data
@Getter
@Setter
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean isOnline;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "assignedTo", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("assignedTo")
    @JsonManagedReference
    private List<Issue> assignedIssues;

    @OneToMany(mappedBy = "resolvedBy", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("resolvedBy")
    @JsonManagedReference
    private List<Issue> resolvedIssues;

    @OneToMany(mappedBy = "rejectedBy", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("rejectedBy")
    @JsonManagedReference
    private List<Issue> rejectedIssues;
}
