package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.Issue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryIssueRepository implements IssueRepository {
    
    private final ConcurrentHashMap<String, Issue> store = new ConcurrentHashMap<>();

    @Override
    public void save(Issue issue) {
        store.put(issue.getIssueId(), issue);
    }

    @Override
    public List<Issue> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Issue findById(String issueId) {
        return store.get(issueId);
    }
}
