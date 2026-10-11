
        package com.greenpulse.impactloop.service;

import com.greenpulse.impactloop.dto.CreateEvidenceRequest;
import com.greenpulse.impactloop.dto.EvidenceResponse;
import com.greenpulse.impactloop.entity.Evidence;
import com.greenpulse.impactloop.repository.EvidenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EvidenceService {

    private final EvidenceRepository evidenceRepository;
    private final S3StorageService s3StorageService;

    public EvidenceService(
            EvidenceRepository evidenceRepository,
            S3StorageService s3StorageService
    ) {
        this.evidenceRepository = evidenceRepository;
        this.s3StorageService = s3StorageService;
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

    public EvidenceResponse uploadEvidence(
            MultipartFile file,
            String interventionId,
            String type,
            Double latitude,
            Double longitude
    ) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        String contentType = file.getContentType();

        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }

        String objectKey = s3StorageService.uploadEvidence(
                file.getInputStream(),
                file.getSize(),
                contentType,
                file.getOriginalFilename()
        );

        String evidenceId = "EV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Evidence evidence = new Evidence(
                evidenceId,
                interventionId,
                type,
                objectKey,
                latitude != null ? latitude : 0.0,
                longitude != null ? longitude : 0.0,
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
