package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.Evidence;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class DynamoDbEvidenceRepository implements EvidenceRepository {

    private final DynamoDbClient dynamoDbClient;

    private static final String TABLE_NAME = "ImpactLoopEvidence";

    public DynamoDbEvidenceRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void save(Evidence evidence) {

        Map<String, AttributeValue> item = new HashMap<>();

        item.put(
                "evidenceId",
                AttributeValue.builder()
                        .s(evidence.getEvidenceId())
                        .build()
        );

        item.put(
                "interventionId",
                AttributeValue.builder()
                        .s(evidence.getInterventionId())
                        .build()
        );

        item.put(
                "type",
                AttributeValue.builder()
                        .s(evidence.getType())
                        .build()
        );

        item.put(
                "photoUrl",
                AttributeValue.builder()
                        .s(evidence.getPhotoUrl())
                        .build()
        );

        item.put(
                "latitude",
                AttributeValue.builder()
                        .n(String.valueOf(evidence.getLatitude()))
                        .build()
        );

        item.put(
                "longitude",
                AttributeValue.builder()
                        .n(String.valueOf(evidence.getLongitude()))
                        .build()
        );

        item.put(
                "timestamp",
                AttributeValue.builder()
                        .s(evidence.getTimestamp())
                        .build()
        );

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
    }

    public Evidence findById(String evidenceId) {

        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(Map.of(
                        "evidenceId",
                        AttributeValue.builder()
                                .s(evidenceId)
                                .build()
                ))
                .build();

        GetItemResponse response =
                dynamoDbClient.getItem(request);

        if (!response.hasItem()) {
            return null;
        }

        Map<String, AttributeValue> item = response.item();

        Evidence evidence = new Evidence();

        evidence.setEvidenceId(
                item.get("evidenceId").s()
        );

        evidence.setInterventionId(
                item.get("interventionId").s()
        );

        evidence.setType(
                item.get("type").s()
        );

        evidence.setPhotoUrl(
                item.get("photoUrl").s()
        );

        evidence.setLatitude(
                Double.parseDouble(
                        item.get("latitude").n()
                )
        );

        evidence.setLongitude(
                Double.parseDouble(
                        item.get("longitude").n()
                )
        );

        evidence.setTimestamp(
                item.get("timestamp").s()
        );

        return evidence;
    }

    public java.util.List<Evidence> findByInterventionId(
            String interventionId
    ) {
        ScanRequest request = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .filterExpression("interventionId = :interventionId")
                .expressionAttributeValues(
                        Map.of(
                                ":interventionId",
                                AttributeValue.builder()
                                        .s(interventionId)
                                        .build()
                        )
                )
                .build();

        ScanResponse response =
                dynamoDbClient.scan(request);

        java.util.List<Evidence> evidenceList =
                new java.util.ArrayList<>();

        for (Map<String, AttributeValue> item : response.items()) {

            Evidence evidence = new Evidence();

            evidence.setEvidenceId(
                    item.get("evidenceId").s()
            );

            evidence.setInterventionId(
                    item.get("interventionId").s()
            );

            evidence.setType(
                    item.get("type").s()
            );

            evidence.setPhotoUrl(
                    item.get("photoUrl").s()
            );

            evidence.setLatitude(
                    Double.parseDouble(
                            item.get("latitude").n()
                    )
            );

            evidence.setLongitude(
                    Double.parseDouble(
                            item.get("longitude").n()
                    )
            );

            evidence.setTimestamp(
                    item.get("timestamp").s()
            );

            evidenceList.add(evidence);
        }

        return evidenceList;
    }
    @Override
    public List<Evidence> findAll() {
        ScanRequest request = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();

        ScanResponse response = dynamoDbClient.scan(request);

        List<Evidence> evidenceList = new ArrayList<>();

        for (Map<String, AttributeValue> item : response.items()) {
            Evidence evidence = new Evidence();

            evidence.setEvidenceId(item.get("evidenceId").s());
            evidence.setInterventionId(item.get("interventionId").s());
            evidence.setType(item.get("type").s());
            evidence.setPhotoUrl(item.get("photoUrl").s());
            evidence.setLatitude(Double.parseDouble(item.get("latitude").n()));
            evidence.setLongitude(Double.parseDouble(item.get("longitude").n()));
            evidence.setTimestamp(item.get("timestamp").s());

            evidenceList.add(evidence);
        }

        return evidenceList;
    }
}
