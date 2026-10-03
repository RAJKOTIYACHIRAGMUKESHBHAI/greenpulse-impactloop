package com.greenpulse.impactloop.service;

import com.greenpulse.impactloop.dto.CreateIssueRequest;
import com.greenpulse.impactloop.dto.IssueResponse;
import com.greenpulse.impactloop.entity.Issue;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class IssueService {

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