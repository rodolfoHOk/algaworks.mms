package br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${app.delivery-events-topic-name}")
    private String deliveryEventsTopicName;

    @Bean
    public NewTopic deliveryEventsTopic() {
        return TopicBuilder.name(deliveryEventsTopicName)
                .partitions(3)
                .replicas(1)
                .build();
    }

}
