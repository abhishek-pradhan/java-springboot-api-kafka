package com.mts.wikiapi;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConfig {

    // Create/configure a ProducerFactory bean that tells Kafka knows how to:
    // - serialize message keys into Strings
    // - serialize message values into Strings
    @Bean
    public ProducerFactory<String, String> producerFactory()
    {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

/*
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-6ojv2.us-west4.gcp.confluent.cloud:9092");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        props.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username='AO22YV3JGCPU4IEI' password='2Zi1atHAKVJt6nsNnpnOrWcIeEDEbvldkbRk87aTAw7qANkxC2lqo6g5ZwQA9MLC';");
*/

        return new DefaultKafkaProducerFactory<>(props);
    }

    // Create a KafkaTemplate bean that uses the ProducerFactory we created above.
    // Our client code will autowire this KafkaTemplate bean, and use it to send messages.
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate()
    {
        return new KafkaTemplate<>(producerFactory());
    }

    // Create/configure a ConsumerFactory bean that tells Kafka knows how to:
    // - deserialize message keys from Strings
    // - deserialize message values from Strings
    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        /*
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"pkc-6ojv2.us-west4.gcp.confluent.cloud:9092");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        props.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username='AO22YV3JGCPU4IEI' password='2Zi1atHAKVJt6nsNnpnOrWcIeEDEbvldkbRk87aTAw7qANkxC2lqo6g5ZwQA9MLC';");
*/
        return new DefaultKafkaConsumerFactory<>(props);
    }

    // Tell Kafka about our ConsumerFactory, so Kafka can create listeners and deserialize messages.
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> concurrentKafkaListenerContainerFactory(ConsumerFactory<String, Object> f) {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(f);
        return factory;
    }
}
