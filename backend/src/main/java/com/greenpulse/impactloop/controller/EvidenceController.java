package com.greenpulse.impactloop.controller;

import com.greenpulse.impactloop.dto.CreateEvidenceRequest;
import com.greenpulse.impactloop.dto.EvidenceResponse;
import com.greenpulse.impactloop.service.EvidenceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evidence")
public class EvidenceController {

    private final EvidenceService evidenceService;

    public EvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EvidenceResponse createEvidence(
            @Valid @RequestBody CreateEvidenceRequest request
    ) {
        return evidenceService.createEvidence(request);
    }

    @GetMapping("/{evidenceId}")
    public EvidenceResponse getEvidence(
            @PathVariable String evidenceId
    ) {
        return evidenceService.getEvidenceById(evidenceId);
    }

    @GetMapping("/intervention/{interventionId}")
    public List<EvidenceResponse> getEvidenceByIntervention(
            @PathVariable String interventionId
    ) {
        return evidenceService.getEvidenceByInterventionId(interventionId);
    }
}
