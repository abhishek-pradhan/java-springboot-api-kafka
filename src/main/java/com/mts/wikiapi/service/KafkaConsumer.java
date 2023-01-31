package com.mts.wikiapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Value("${kafka.topic}")
    private String topic;

    @KafkaListener(topics = "topic_1", groupId = "group1")
    public void group1ConsumerA(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timestamp,
            @Header(KafkaHeaders.OFFSET) String offset,
            String value){
        logger.info(String.format("********** MyConsumer group1ConsumerA consumed message: key %s, topic %s, timestamp %s, offset %s, value %s", key, topic, timestamp, offset, value));
    }

    @KafkaListener(topics = "topic_1", groupId = "group1")
    public void group1ConsumerB(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timestamp,
            @Header(KafkaHeaders.OFFSET) String offset,
            String value){
        logger.info(String.format("********** MyConsumer group1ConsumerB consumed message: key %s, topic %s, timestamp %s, offset %s, value %s", key, topic, timestamp, offset, value));
    }

    @KafkaListener(topics = "topic_1", groupId = "group2")
    public void group2ConsumerA(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timestamp,
            @Header(KafkaHeaders.OFFSET) String offset,
            String value){
        logger.info(String.format("********** MyConsumer group2ConsumerA consumed message: key %s, topic %s, timestamp %s, offset %s, value %s", key, topic, timestamp, offset, value));
    }
}