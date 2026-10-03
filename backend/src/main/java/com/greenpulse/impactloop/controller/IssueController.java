package com.greenpulse.impactloop.controller;

import com.greenpulse.impactloop.dto.CreateIssueRequest;
import com.greenpulse.impactloop.dto.IssueResponse;
import com.greenpulse.impactloop.service.IssueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IssueResponse createIssue(
            @Valid @RequestBody CreateIssueRequest request
    ) {
        return issueService.createIssue(request);
    }
}