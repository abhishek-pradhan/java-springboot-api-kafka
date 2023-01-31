package com.mts.wikiapi.events;

import lombok.Data;

@Data
public class DomainEvent extends BaseEvent {
    private boolean isError;
    private String message;
    private WikiEntity wikiEntity;
}
