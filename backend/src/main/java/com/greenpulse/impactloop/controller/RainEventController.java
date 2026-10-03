package com.greenpulse.impactloop.controller;

import com.greenpulse.impactloop.dto.CreateRainEventRequest;
import com.greenpulse.impactloop.dto.RainEventResponse;
import com.greenpulse.impactloop.service.RainEventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rain-events")
public class RainEventController {

    private final RainEventService rainEventService;

    public RainEventController(RainEventService rainEventService) {
        this.rainEventService = rainEventService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RainEventResponse createRainEvent(
            @Valid @RequestBody CreateRainEventRequest request
    ) {
        return rainEventService.createRainEvent(request);
    }

    @GetMapping
    public List<RainEventResponse> getAllRainEvents() {
        return rainEventService.getAllRainEvents();
    }

    @GetMapping("/{eventId}")
    public RainEventResponse getRainEvent(
            @PathVariable String eventId
    ) {
        return rainEventService.getRainEventById(eventId);
    }
}
