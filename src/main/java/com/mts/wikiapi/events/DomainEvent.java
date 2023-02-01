package com.mts.wikiapi.events;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

// DomainEvent is based on DDD's Domain Event & EI's Document Message patterns
@Data
public class DomainEvent {
    private final UUID uuid;
    private final Date createdTime;
    private boolean isError;
    private String message;

    // each Event will have specific Domain's data, in this case it's Wiki Domain data
    private WikiEntity wikiEntity;

    public DomainEvent() {
        this.uuid = UUID.randomUUID();
        this.createdTime = new Date();
        this.isError = false;
        this.message = "Default event created";
    }
}
