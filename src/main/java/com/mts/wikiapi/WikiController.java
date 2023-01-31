package com.mts.wikiapi;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@RestController
@RequestMapping("/api")
public class WikiController {
    private static final Logger logger = LoggerFactory.getLogger(WikiController.class);

    @Autowired
    private WikiKafkaProducer wikiKafkaProducer;

    @GetMapping("/pub")
    public String publish(@RequestParam("value") String value) {
        for (int i = 1; i <= 5000; i++) {
            this.wikiKafkaProducer.sendMessage("key" + i, value);
        }
        return "Published 5 messages, keys: 1-5, value: " + value;
    }
}
