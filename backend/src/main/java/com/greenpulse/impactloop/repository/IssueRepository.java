package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.Issue;
import java.util.List;

public interface IssueRepository {
    void save(Issue issue);
    List<Issue> findAll();
    Issue findById(String issueId);
}
