package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.Issue;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;



import java.util.HashMap;
import java.util.Map;

@Repository
@Primary
public class DynamoDbIssueRepository implements IssueRepository {

    private final DynamoDbClient dynamoDbClient;

    private static final String TABLE_NAME = "ImpactLoopIssues";

    public DynamoDbIssueRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void save(Issue issue) {

        Map<String, AttributeValue> item = new HashMap<>();

        item.put("issueId", AttributeValue.builder()
                .s(issue.getIssueId())
                .build());

        item.put("type", AttributeValue.builder()
                .s(issue.getType())
                .build());

        item.put("latitude", AttributeValue.builder()
                .n(String.valueOf(issue.getLatitude()))
                .build());

        item.put("longitude", AttributeValue.builder()
                .n(String.valueOf(issue.getLongitude()))
                .build());

        item.put("description", AttributeValue.builder()
                .s(issue.getDescription())
                .build());

        if (issue.getPhotoUrl() != null) {
            item.put("photoUrl", AttributeValue.builder()
                    .s(issue.getPhotoUrl())
                    .build());
        }

        item.put("status", AttributeValue.builder()
                .s(issue.getStatus())
                .build());

        item.put("createdAt", AttributeValue.builder()
                .s(issue.getCreatedAt())
                .build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
    }

    public java.util.List<Issue> findAll() {

        ScanRequest request = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();

        ScanResponse response = dynamoDbClient.scan(request);

        java.util.List<Issue> issues = new java.util.ArrayList<>();

        for (Map<String, AttributeValue> item : response.items()) {

            Issue issue = new Issue();

            issue.setIssueId(item.get("issueId").s());
            issue.setType(item.get("type").s());
            issue.setLatitude(Double.parseDouble(item.get("latitude").n()));
            issue.setLongitude(Double.parseDouble(item.get("longitude").n()));
            issue.setDescription(item.get("description").s());

            if (item.containsKey("photoUrl")) {
                issue.setPhotoUrl(item.get("photoUrl").s());
            }

            issue.setStatus(item.get("status").s());
            issue.setCreatedAt(item.get("createdAt").s());

            issues.add(issue);
        }

        return issues;
    }

    public Issue findById(String issueId) {

        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(Map.of(
                        "issueId",
                        AttributeValue.builder().s(issueId).build()
                ))
                .build();

        GetItemResponse response = dynamoDbClient.getItem(request);

        if (!response.hasItem()) {
            return null;
        }

        Map<String, AttributeValue> item = response.item();

        Issue issue = new Issue();
        issue.setIssueId(item.get("issueId").s());
        issue.setType(item.get("type").s());
        issue.setLatitude(Double.parseDouble(item.get("latitude").n()));
        issue.setLongitude(Double.parseDouble(item.get("longitude").n()));
        issue.setDescription(item.get("description").s());

        if (item.containsKey("photoUrl")) {
            issue.setPhotoUrl(item.get("photoUrl").s());
        }

        issue.setStatus(item.get("status").s());
        issue.setCreatedAt(item.get("createdAt").s());

        return issue;
    }

}
