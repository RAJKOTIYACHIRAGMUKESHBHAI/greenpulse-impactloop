package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.RainEvent;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class DynamoDbRainEventRepository implements RainEventRepository {

    private final DynamoDbClient dynamoDbClient;

    private static final String TABLE_NAME = "ImpactLoopRainEvents";

    public DynamoDbRainEventRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void save(RainEvent event) {

        Map<String, AttributeValue> item = new HashMap<>();

        item.put(
                "eventId",
                AttributeValue.builder().s(event.getEventId()).build()
        );

        item.put(
                "location",
                AttributeValue.builder().s(event.getLocation()).build()
        );

        item.put(
                "startTime",
                AttributeValue.builder().s(event.getStartTime()).build()
        );

        item.put(
                "endTime",
                AttributeValue.builder().s(event.getEndTime()).build()
        );

        item.put(
                "rainfallMm",
                AttributeValue.builder()
                        .n(String.valueOf(event.getRainfallMm()))
                        .build()
        );

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
    }

    public List<RainEvent> findAll() {

        ScanRequest request = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();

        ScanResponse response = dynamoDbClient.scan(request);

        List<RainEvent> events = new ArrayList<>();

        for (Map<String, AttributeValue> item : response.items()) {

            RainEvent event = new RainEvent();

            event.setEventId(item.get("eventId").s());
            event.setLocation(item.get("location").s());
            event.setStartTime(item.get("startTime").s());
            event.setEndTime(item.get("endTime").s());
            event.setRainfallMm(
                    Double.parseDouble(item.get("rainfallMm").n())
            );

            events.add(event);
        }

        return events;
    }

    public RainEvent findById(String eventId) {

        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(Map.of(
                        "eventId",
                        AttributeValue.builder().s(eventId).build()
                ))
                .build();

        GetItemResponse response = dynamoDbClient.getItem(request);

        if (!response.hasItem()) {
            return null;
        }

        Map<String, AttributeValue> item = response.item();

        RainEvent event = new RainEvent();

        event.setEventId(item.get("eventId").s());
        event.setLocation(item.get("location").s());
        event.setStartTime(item.get("startTime").s());
        event.setEndTime(item.get("endTime").s());
        event.setRainfallMm(
                Double.parseDouble(item.get("rainfallMm").n())
        );

        return event;
    }
    @Override
    public List<RainEvent> findByLocation(String location) {

        for (RainEvent event : findAll()) {
            if (event.getLocation().equals(location)) {
                // Keep all matching events, so rebuild the list below.
            }
        }

        List<RainEvent> matching = new ArrayList<>();

        for (RainEvent event : findAll()) {
            if (event.getLocation().equals(location)) {
                matching.add(event);
            }
        }

        return matching;
    }
}
