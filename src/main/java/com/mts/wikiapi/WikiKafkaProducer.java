package com.mts.wikiapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WikiKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(WikiKafkaProducer.class);
    private static final String TOPIC_NAME = "topic_1";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String key, String value){
        logger.info(String.format("********** MyPublisher is sending message: %s:%s", key, value));
        this.kafkaTemplate.send(TOPIC_NAME, key, value);
    }
}