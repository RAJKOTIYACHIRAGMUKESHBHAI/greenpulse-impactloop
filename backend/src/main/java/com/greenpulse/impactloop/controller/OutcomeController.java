package com.greenpulse.impactloop.controller;

import com.greenpulse.impactloop.dto.CreateOutcomeRequest;
import com.greenpulse.impactloop.dto.OutcomeResponse;
import com.greenpulse.impactloop.service.OutcomeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/outcomes")
public class OutcomeController {

    private final OutcomeService outcomeService;

    public OutcomeController(OutcomeService outcomeService) {
        this.outcomeService = outcomeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OutcomeResponse createOutcome(
            @Valid @RequestBody CreateOutcomeRequest request
    ) {
        return outcomeService.createOutcome(request);
    }

    @GetMapping("/{outcomeId}")
    public OutcomeResponse getOutcome(
            @PathVariable String outcomeId
    ) {
        return outcomeService.getOutcomeById(outcomeId);
    }
}
