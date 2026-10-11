package com.greenpulse.impactloop.controller;

import com.greenpulse.impactloop.dto.CreateEvidenceRequest;
import com.greenpulse.impactloop.dto.EvidenceResponse;
import com.greenpulse.impactloop.service.EvidenceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public EvidenceResponse uploadEvidence(
            @RequestParam("file") MultipartFile file,
            @RequestParam("interventionId") String interventionId,
            @RequestParam("type") String type,
            @RequestParam(value = "latitude", required = false) Double latitude,
            @RequestParam(value = "longitude", required = false) Double longitude
    ) throws IOException {
        return evidenceService.uploadEvidence(
                file,
                interventionId,
                type,
                latitude,
                longitude
        );
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
