package com.mts.wikiapi.service;

import com.mts.wikiapi.events.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private final String topicName;

    public KafkaProducer(@Value("${kafka.producer.topic}") final String topicName) {
        this.topicName = topicName;
    }

    @Autowired
    private KafkaTemplate<String, DomainEvent> kafkaTemplate;

    public void sendMessage(String key, DomainEvent value){
        this.kafkaTemplate.send(topicName, key, value);

        logger.info(String.format("***** MyPublisher sent message: key=%s and value=%s *****", key, value));

        // todo: write business logic to act on this event
    }
}