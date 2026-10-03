package com.greenpulse.impactloop.controller;

import com.greenpulse.impactloop.dto.CreateInterventionRequest;
import com.greenpulse.impactloop.dto.InterventionResponse;
import com.greenpulse.impactloop.dto.UpdateInterventionRequest;
import com.greenpulse.impactloop.service.InterventionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/interventions")
public class InterventionController {

    private final InterventionService interventionService;

    public InterventionController(InterventionService interventionService) {
        this.interventionService = interventionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InterventionResponse createIntervention(
            @Valid @RequestBody CreateInterventionRequest request
    ) {
        return interventionService.createIntervention(request);
    }

    @GetMapping("/{interventionId}")
    public InterventionResponse getIntervention(
            @PathVariable String interventionId
    ) {
        return interventionService.getInterventionById(interventionId);
    }

    @PatchMapping("/{interventionId}")
    public InterventionResponse updateIntervention(
            @PathVariable String interventionId,
            @Valid @RequestBody UpdateInterventionRequest request
    ) {
        return interventionService.updateIntervention(interventionId, request);
    }
}
