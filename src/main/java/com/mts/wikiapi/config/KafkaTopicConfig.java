package com.mts.wikiapi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.consumer.topic}")
    private String topic;

    // Kafka Admin creates topic, if it doesn't exist.
    // A KafkaAdmin bean is responsible for creating new topics in our broker.
    // With Spring Boot, a KafkaAdmin bean is automatically registered.
    @Bean
    public NewTopic newTopic()
    {
        return TopicBuilder.name(topic).build();
    }
}
