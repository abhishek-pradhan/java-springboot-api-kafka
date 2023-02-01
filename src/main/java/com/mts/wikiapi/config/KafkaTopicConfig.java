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

    // Create/configure a NewTopic bean that configures Kafka to:
    // - Kafka Admin creates new topic, if it doesn't exist.
    @Bean
    public NewTopic newTopic()
    {
        return TopicBuilder.name(topic).build();
    }
}
