package br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.event;

import br.com.hiokdev.algadelivery.delivery.tracking.domain.event.DeliveryFulfilledEvent;
import br.com.hiokdev.algadelivery.delivery.tracking.domain.event.DeliveryPickUpEvent;
import br.com.hiokdev.algadelivery.delivery.tracking.domain.event.DeliveryPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeliveryDomainEventHandler {

    private final IntegrationEventPublisher integrationEventPublisher;

    @Value("${app.delivery-events-topic-name}")
    private String deliveryEventsTopicName;

    @EventListener
    public void handleDeliveryPlacedEvent(DeliveryPlacedEvent event) {
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(), deliveryEventsTopicName);
    }

    @EventListener
    public void handleDeliveryPickUpEvent(DeliveryPickUpEvent event) {
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(), deliveryEventsTopicName);
    }

    @EventListener
    public void handleDeliveryFulfilledEvent(DeliveryFulfilledEvent event) {
        log.info(event.toString());
        integrationEventPublisher.publish(event, event.getDeliveryId().toString(), deliveryEventsTopicName);
    }

}
