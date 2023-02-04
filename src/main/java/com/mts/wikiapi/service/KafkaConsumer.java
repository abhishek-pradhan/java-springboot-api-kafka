package com.mts.wikiapi.service;

import com.mts.wikiapi.events.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void receiveMessage(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timestamp,
            @Header(KafkaHeaders.OFFSET) String offset,
            DomainEvent value) {

        logger.info(String.format("***** MyConsumer consumed message: key=%s, topic=%s, timestamp=%s, offset=%s, value=%s *****", key, topic, timestamp, offset, value));

        // todo: write business logic to act on this event
    }
}