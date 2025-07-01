package com.aust.its.repository;

import com.aust.its.entity.Developer;
import com.aust.its.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    @Query("SELECT d FROM Developer d LEFT JOIN FETCH d.assignedIssues WHERE d.id = :id")
    Optional<Developer> findByIdWithAssignedIssues(@Param("id") Long id);

    @Query("SELECT d FROM Developer d LEFT JOIN FETCH d.assignedIssues WHERE d.user = :user")
    Optional<Developer> findByUserWithAssignedIssues(@Param("user") User user);



    Developer findByUser(User user);
}
