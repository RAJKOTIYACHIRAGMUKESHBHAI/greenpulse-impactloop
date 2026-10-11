package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.Outcome;
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
public class DynamoDbOutcomeRepository implements OutcomeRepository {

    private final DynamoDbClient dynamoDbClient;

    private static final String TABLE_NAME = "ImpactLoopOutcomes";

    public DynamoDbOutcomeRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void save(Outcome outcome) {

        Map<String, AttributeValue> item = new HashMap<>();

        item.put(
                "outcomeId",
                AttributeValue.builder()
                        .s(outcome.getOutcomeId())
                        .build()
        );

        item.put(
                "interventionId",
                AttributeValue.builder()
                        .s(outcome.getInterventionId())
                        .build()
        );

        item.put(
                "outcome",
                AttributeValue.builder()
                        .s(outcome.getOutcome())
                        .build()
        );

        item.put(
                "beforeIncidents",
                AttributeValue.builder()
                        .n(String.valueOf(outcome.getBeforeIncidents()))
                        .build()
        );

        item.put(
                "afterIncidents",
                AttributeValue.builder()
                        .n(String.valueOf(outcome.getAfterIncidents()))
                        .build()
        );

        item.put(
                "observedReduction",
                AttributeValue.builder()
                        .n(String.valueOf(outcome.getObservedReduction()))
                        .build()
        );

        if (outcome.getDurationReduction() != null) {
            item.put(
                    "durationReduction",
                    AttributeValue.builder()
                            .n(String.valueOf(outcome.getDurationReduction()))
                            .build()
            );
        }

        item.put(
                "confidence",
                AttributeValue.builder()
                        .s(outcome.getConfidence())
                        .build()
        );

        item.put(
                "recurring",
                AttributeValue.builder()
                        .bool(outcome.isRecurring())
                        .build()
        );

        item.put(
                "nextAction",
                AttributeValue.builder()
                        .s(outcome.getNextAction())
                        .build()
        );

        item.put(
                "createdAt",
                AttributeValue.builder()
                        .s(outcome.getCreatedAt())
                        .build()
        );

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
    }

    public Outcome findById(String outcomeId) {

        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(
                        Map.of(
                                "outcomeId",
                                AttributeValue.builder()
                                        .s(outcomeId)
                                        .build()
                        )
                )
                .build();

        GetItemResponse response =
                dynamoDbClient.getItem(request);

        if (!response.hasItem()) {
            return null;
        }

        Map<String, AttributeValue> item = response.item();

        Outcome outcome = new Outcome();

        outcome.setOutcomeId(
                item.get("outcomeId").s()
        );

        outcome.setInterventionId(
                item.get("interventionId").s()
        );

        outcome.setOutcome(
                item.get("outcome").s()
        );

        outcome.setBeforeIncidents(
                Integer.parseInt(
                        item.get("beforeIncidents").n()
                )
        );

        outcome.setAfterIncidents(
                Integer.parseInt(
                        item.get("afterIncidents").n()
                )
        );

        outcome.setObservedReduction(
                Double.parseDouble(
                        item.get("observedReduction").n()
                )
        );

        if (item.containsKey("durationReduction")) {
            outcome.setDurationReduction(
                    Double.parseDouble(
                            item.get("durationReduction").n()
                    )
            );
        }

        outcome.setConfidence(
                item.get("confidence").s()
        );

        outcome.setRecurring(
                item.get("recurring").bool()
        );

        outcome.setNextAction(
                item.get("nextAction").s()
        );

        outcome.setCreatedAt(
                item.get("createdAt").s()
        );

        return outcome;
    }
    @Override
    public List<Outcome> findAll() {

        ScanRequest request = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();

        ScanResponse response = dynamoDbClient.scan(request);

        List<Outcome> outcomes = new ArrayList<>();

        for (Map<String, AttributeValue> item : response.items()) {

            Outcome outcome = new Outcome();

            outcome.setOutcomeId(item.get("outcomeId").s());
            outcome.setInterventionId(item.get("interventionId").s());
            outcome.setOutcome(item.get("outcome").s());
            outcome.setBeforeIncidents(Integer.parseInt(item.get("beforeIncidents").n()));
            outcome.setAfterIncidents(Integer.parseInt(item.get("afterIncidents").n()));
            outcome.setObservedReduction(Double.parseDouble(item.get("observedReduction").n()));

            if (item.containsKey("durationReduction")) {
                outcome.setDurationReduction(
                        Double.parseDouble(item.get("durationReduction").n())
                );
            }

            outcome.setConfidence(item.get("confidence").s());
            outcome.setRecurring(item.get("recurring").bool());
            outcome.setNextAction(item.get("nextAction").s());
            outcome.setCreatedAt(item.get("createdAt").s());

            outcomes.add(outcome);
        }

        return outcomes;
    }

    @Override
    public Outcome findByInterventionId(String interventionId) {

        for (Outcome outcome : findAll()) {
            if (outcome.getInterventionId().equals(interventionId)) {
                return outcome;
            }
        }

        return null;
    }
}
