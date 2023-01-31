package com.mts.wikiapi.service;

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
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String key, String value){
        logger.info(String.format("********** MyPublisher is sending message: %s:%s", key, value));
        this.kafkaTemplate.send(topicName, key, value);
    }
}