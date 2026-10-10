package com.greenpulse.impactloop.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class S3StorageService {

    private static final String BUCKET_NAME =
            "impactloop-evidence-877133239869";

    private final S3Client s3Client;

    public S3StorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadEvidence(
            InputStream inputStream,
            long contentLength,
            String contentType,
            String originalFileName
    ) throws IOException {

        String objectKey =
                "evidence/" + UUID.randomUUID() + "-" + originalFileName;

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(objectKey)
                .contentType(contentType)
                .build();

        s3Client.putObject(
                request,
                RequestBody.fromInputStream(
                        inputStream,
                        contentLength
                )
        );

        return objectKey;
    }
}