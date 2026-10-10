package com.greenpulse.impactloop.service;

import com.greenpulse.impactloop.dto.CreateEvidenceRequest;
import com.greenpulse.impactloop.dto.EvidenceResponse;
import com.greenpulse.impactloop.entity.Evidence;
import com.greenpulse.impactloop.repository.EvidenceRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EvidenceService {

    private final EvidenceRepository evidenceRepository;

    public EvidenceService(EvidenceRepository evidenceRepository) {
        this.evidenceRepository = evidenceRepository;
    }

    public EvidenceResponse createEvidence(CreateEvidenceRequest request) {
        String evidenceId = "EV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
        Evidence evidence = new Evidence(
                evidenceId,
                request.getInterventionId(),
                request.getType(),
                request.getPhotoUrl(),
                request.getLatitude() != null ? request.getLatitude() : 0.0,
                request.getLongitude() != null ? request.getLongitude() : 0.0,
                Instant.now().toString()
        );

        evidenceRepository.save(evidence);
        return toResponse(evidence);
    }

    public EvidenceResponse getEvidenceById(String evidenceId) {
        Evidence evidence = evidenceRepository.findById(evidenceId);
        if (evidence == null) {
            return null;
        }
        return toResponse(evidence);
    }

    public List<EvidenceResponse> getEvidenceByInterventionId(String interventionId) {
        List<Evidence> evidences = evidenceRepository.findByInterventionId(interventionId);
        List<EvidenceResponse> responses = new ArrayList<>();
        
        for (Evidence evidence : evidences) {
            responses.add(toResponse(evidence));
        }
        
        return responses;
    }

    private EvidenceResponse toResponse(Evidence evidence) {
        return new EvidenceResponse(
                evidence.getEvidenceId(),
                evidence.getInterventionId(),
                evidence.getType(),
                evidence.getPhotoUrl(),
                evidence.getLatitude(),
                evidence.getLongitude(),
                evidence.getTimestamp()
        );
    }
}
