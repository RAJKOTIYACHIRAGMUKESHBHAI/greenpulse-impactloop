package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.RainEvent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryRainEventRepository implements RainEventRepository {
    
    private final ConcurrentHashMap<String, RainEvent> store = new ConcurrentHashMap<>();

    @Override
    public void save(RainEvent rainEvent) {
        store.put(rainEvent.getEventId(), rainEvent);
    }

    @Override
    public List<RainEvent> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public RainEvent findById(String eventId) {
        return store.get(eventId);
    }

    @Override
    public List<RainEvent> findByLocation(String location) {
        return store.values().stream()
                .filter(r -> r.getLocation().equals(location))
                .collect(Collectors.toList());
    }
}
