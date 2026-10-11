package com.greenpulse.impactloop.repository;

import com.greenpulse.impactloop.entity.RainEvent;
import java.util.List;

public interface RainEventRepository {
    void save(RainEvent rainEvent);
    List<RainEvent> findAll();
    RainEvent findById(String eventId);
    List<RainEvent> findByLocation(String location);
}
