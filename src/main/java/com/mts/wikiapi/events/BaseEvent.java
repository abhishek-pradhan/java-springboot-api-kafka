package com.mts.wikiapi.events;

import org.apache.kafka.common.security.oauthbearer.internals.unsecured.OAuthBearerUnsecuredJws;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public abstract class BaseEvent {
    private final UUID uuid;
    private final Date createdTime;

    public BaseEvent() {
        this.uuid = UUID.randomUUID();
        this.createdTime = new Date();
    }
}
