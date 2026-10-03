package com.greenpulse.impactloop.service;

import com.greenpulse.impactloop.dto.CreateRainEventRequest;
import com.greenpulse.impactloop.dto.RainEventResponse;
import com.greenpulse.impactloop.entity.RainEvent;
import com.greenpulse.impactloop.repository.RainEventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RainEventService {

    private final RainEventRepository rainEventRepository;

    public RainEventService(RainEventRepository rainEventRepository) {
        this.rainEventRepository = rainEventRepository;
    }

    public RainEventResponse createRainEvent(CreateRainEventRequest request) {
        String eventId = "RAIN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
        RainEvent rainEvent = new RainEvent(
                eventId,
                request.getLocation(),
                request.getStartTime(),
                request.getEndTime(),
                request.getRainfallMm() != null ? request.getRainfallMm() : 0.0
        );

        rainEventRepository.save(rainEvent);
        return toResponse(rainEvent);
    }

    public RainEventResponse getRainEventById(String eventId) {
        RainEvent rainEvent = rainEventRepository.findById(eventId);
        if (rainEvent == null) {
            return null;
        }
        return toResponse(rainEvent);
    }

    public List<RainEventResponse> getAllRainEvents() {
        List<RainEvent> rainEvents = rainEventRepository.findAll();
        List<RainEventResponse> responses = new ArrayList<>();
        
        for (RainEvent rainEvent : rainEvents) {
            responses.add(toResponse(rainEvent));
        }
        
        return responses;
    }

    public List<RainEventResponse> getRainEventsByLocation(String location) {
        List<RainEvent> rainEvents = rainEventRepository.findByLocation(location);
        List<RainEventResponse> responses = new ArrayList<>();
        
        for (RainEvent rainEvent : rainEvents) {
            responses.add(toResponse(rainEvent));
        }
        
        return responses;
    }

    private RainEventResponse toResponse(RainEvent rainEvent) {
        return new RainEventResponse(
                rainEvent.getEventId(),
                rainEvent.getLocation(),
                rainEvent.getStartTime(),
                rainEvent.getEndTime(),
                rainEvent.getRainfallMm()
        );
    }
}
