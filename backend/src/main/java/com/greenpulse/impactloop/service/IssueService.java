package com.greenpulse.impactloop.service;

import com.greenpulse.impactloop.dto.CreateIssueRequest;
import com.greenpulse.impactloop.dto.IssueResponse;
import com.greenpulse.impactloop.entity.Issue;
import com.greenpulse.impactloop.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public IssueResponse createIssue(CreateIssueRequest request) {

        String issueId = "ISS-" + UUID.randomUUID();
        String createdAt = Instant.now().toString();

        Issue issue = new Issue(
                issueId,
                request.getType(),
                request.getLatitude(),
                request.getLongitude(),
                request.getDescription(),
                request.getPhotoUrl(),
                "OPEN",
                createdAt
        );

        issueRepository.save(issue);

        return toResponse(issue);
    }

    public List<IssueResponse> getAllIssues() {

        List<Issue> issues = issueRepository.findAll();
        List<IssueResponse> responses = new ArrayList<>();

        for (Issue issue : issues) {
            responses.add(toResponse(issue));
        }

        return responses;
    }

    public IssueResponse getIssueById(String issueId) {

        Issue issue = issueRepository.findById(issueId);

        if (issue == null) {
            return null;
        }

        return toResponse(issue);
    }

    private IssueResponse toResponse(Issue issue) {

        return new IssueResponse(
                issue.getIssueId(),
                issue.getType(),
                issue.getLatitude(),
                issue.getLongitude(),
                issue.getDescription(),
                issue.getPhotoUrl(),
                issue.getStatus(),
                issue.getCreatedAt()
        );
    }
}