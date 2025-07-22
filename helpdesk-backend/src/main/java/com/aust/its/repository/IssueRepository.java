package com.aust.its.repository;

import com.aust.its.entity.Issue;
import com.aust.its.enums.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findByUserIdAndStatus(Long userId, IssueStatus status);
    List<Issue> findByStatus(IssueStatus status);
    List<Issue> findByCreatedAtBetweenOrderByCreatedAtAsc(LocalDateTime start, LocalDateTime end);


}
