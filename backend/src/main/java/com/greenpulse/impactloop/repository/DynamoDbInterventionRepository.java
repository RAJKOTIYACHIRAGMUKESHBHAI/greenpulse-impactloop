package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.Intervention;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class DynamoDbInterventionRepository implements InterventionRepository {

    private final DynamoDbClient dynamoDbClient;

    private static final String TABLE_NAME = "ImpactLoopInterventions";

    public DynamoDbInterventionRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void save(Intervention intervention) {

        Map<String, AttributeValue> item = new HashMap<>();

        item.put(
                "interventionId",
                AttributeValue.builder()
                        .s(intervention.getInterventionId())
                        .build()
        );

        item.put(
                "issueId",
                AttributeValue.builder()
                        .s(intervention.getIssueId())
                        .build()
        );

        item.put(
                "actionType",
                AttributeValue.builder()
                        .s(intervention.getActionType())
                        .build()
        );

        if (intervention.getAssignedTeam() != null) {
            item.put(
                    "assignedTeam",
                    AttributeValue.builder()
                            .s(intervention.getAssignedTeam())
                            .build()
            );
        }

        item.put(
                "status",
                AttributeValue.builder()
                        .s(intervention.getStatus())
                        .build()
        );

        if (intervention.getStartedAt() != null) {
            item.put(
                    "startedAt",
                    AttributeValue.builder()
                            .s(intervention.getStartedAt())
                            .build()
            );
        }

        if (intervention.getCompletedAt() != null) {
            item.put(
                    "completedAt",
                    AttributeValue.builder()
                            .s(intervention.getCompletedAt())
                            .build()
            );
        }

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
    }

    public void update(Intervention intervention) {

        Map<String, AttributeValue> key = Map.of(
                "interventionId",
                AttributeValue.builder()
                        .s(intervention.getInterventionId())
                        .build()
        );

        Map<String, String> expressionNames = new HashMap<>();
        Map<String, AttributeValue> expressionValues = new HashMap<>();

        expressionNames.put("#status", "status");

        expressionValues.put(
                ":status",
                AttributeValue.builder()
                        .s(intervention.getStatus())
                        .build()
        );

        String updateExpression = "SET #status = :status";

        if (intervention.getStartedAt() != null) {

            expressionNames.put("#startedAt", "startedAt");

            expressionValues.put(
                    ":startedAt",
                    AttributeValue.builder()
                            .s(intervention.getStartedAt())
                            .build()
            );

            updateExpression += ", #startedAt = :startedAt";
        }

        if (intervention.getCompletedAt() != null) {

            expressionNames.put("#completedAt", "completedAt");

            expressionValues.put(
                    ":completedAt",
                    AttributeValue.builder()
                            .s(intervention.getCompletedAt())
                            .build()
            );

            updateExpression += ", #completedAt = :completedAt";
        }

        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .updateExpression(updateExpression)
                .expressionAttributeNames(expressionNames)
                .expressionAttributeValues(expressionValues)
                .build();

        dynamoDbClient.updateItem(request);
    }

    public Intervention findById(String interventionId) {

        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(Map.of(
                        "interventionId",
                        AttributeValue.builder()
                                .s(interventionId)
                                .build()
                ))
                .build();

        GetItemResponse response = dynamoDbClient.getItem(request);

        if (!response.hasItem()) {
            return null;
        }

        Map<String, AttributeValue> item = response.item();

        Intervention intervention = new Intervention();

        intervention.setInterventionId(
                item.get("interventionId").s()
        );

        intervention.setIssueId(
                item.get("issueId").s()
        );

        intervention.setActionType(
                item.get("actionType").s()
        );

        if (item.containsKey("assignedTeam")) {
            intervention.setAssignedTeam(
                    item.get("assignedTeam").s()
            );
        }

        intervention.setStatus(
                item.get("status").s()
        );

        if (item.containsKey("startedAt")) {
            intervention.setStartedAt(
                    item.get("startedAt").s()
            );
        }

        if (item.containsKey("completedAt")) {
            intervention.setCompletedAt(
                    item.get("completedAt").s()
            );
        }

        return intervention;
    }
    @Override
    public List<Intervention> findAll() {

        ScanRequest request = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();

        ScanResponse response = dynamoDbClient.scan(request);

        List<Intervention> interventions = new ArrayList<>();

        for (Map<String, AttributeValue> item : response.items()) {

            Intervention intervention = new Intervention();

            intervention.setInterventionId(item.get("interventionId").s());
            intervention.setIssueId(item.get("issueId").s());
            intervention.setActionType(item.get("actionType").s());

            if (item.containsKey("assignedTeam")) {
                intervention.setAssignedTeam(item.get("assignedTeam").s());
            }

            intervention.setStatus(item.get("status").s());

            if (item.containsKey("startedAt")) {
                intervention.setStartedAt(item.get("startedAt").s());
            }

            if (item.containsKey("completedAt")) {
                intervention.setCompletedAt(item.get("completedAt").s());
            }

            interventions.add(intervention);
        }

        return interventions;
    }

    @Override
    public List<Intervention> findByIssueId(String issueId) {

        ScanRequest request = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .filterExpression("issueId = :issueId")
                .expressionAttributeValues(
                        Map.of(
                                ":issueId",
                                AttributeValue.builder()
                                        .s(issueId)
                                        .build()
                        )
                )
                .build();

        ScanResponse response = dynamoDbClient.scan(request);

        List<Intervention> interventions = new ArrayList<>();

        for (Map<String, AttributeValue> item : response.items()) {

            Intervention intervention = new Intervention();

            intervention.setInterventionId(item.get("interventionId").s());
            intervention.setIssueId(item.get("issueId").s());
            intervention.setActionType(item.get("actionType").s());

            if (item.containsKey("assignedTeam")) {
                intervention.setAssignedTeam(item.get("assignedTeam").s());
            }

            intervention.setStatus(item.get("status").s());

            if (item.containsKey("startedAt")) {
                intervention.setStartedAt(item.get("startedAt").s());
            }

            if (item.containsKey("completedAt")) {
                intervention.setCompletedAt(item.get("completedAt").s());
            }

            interventions.add(intervention);
        }

        return interventions;
    }
}
