package com.mts.wikiapi.config;

import com.mts.wikiapi.events.DomainEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // Create/configure a ConsumerFactory bean that configures Kafka to:
    // - deserialize message key from String
    // - deserialize message value from json
    @Bean
    public ConsumerFactory<String, DomainEvent> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //todo: not sure why below property is not getting applied via code, its working from app.properties file
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        //props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    // Tell Kafka about our ConsumerFactory, so Kafka can create listeners and deserialize messages.
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DomainEvent> concurrentKafkaListenerContainerFactory(ConsumerFactory<String, DomainEvent> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, DomainEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
