package br.com.hiokdev.algadelivery.courier.management.infrastructure.kafka;

import br.com.hiokdev.algadelivery.courier.management.application.service.CourierDeliveryService;
import br.com.hiokdev.algadelivery.courier.management.infrastructure.event.DeliveryFulfilledIntegrationEvent;
import br.com.hiokdev.algadelivery.courier.management.infrastructure.event.DeliveryPlacedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = { "deliveries.v1.events" }, groupId = "courier-management")
@Slf4j
@RequiredArgsConstructor
public class KafkaDeliveriesMessageHandler {

    private final CourierDeliveryService courierDeliveryService;

    @KafkaHandler(isDefault = true)
    public void defaultHandler(@Payload Object object) {
        log.info("Default Handler: {}", object);
    }

    @KafkaHandler
    public void handleDeliveryPlacedIntegrationEvent(@Payload DeliveryPlacedIntegrationEvent event) {
        log.info("Received delivery place: {}", event);
        courierDeliveryService.assign(event.getDeliveryId());
    }

    @KafkaHandler
    public void handleDeliveryFulfilledIntegrationEvent(@Payload DeliveryFulfilledIntegrationEvent event) {
        log.info("Received: delivery fulfill: {}", event);
        courierDeliveryService.fulfill(event.getDeliveryId());
    }

}
