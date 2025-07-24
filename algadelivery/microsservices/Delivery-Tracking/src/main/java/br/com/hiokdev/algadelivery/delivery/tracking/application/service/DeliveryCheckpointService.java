package br.com.hiokdev.algadelivery.delivery.tracking.application.service;

import br.com.hiokdev.algadelivery.delivery.tracking.domain.exception.DomainException;
import br.com.hiokdev.algadelivery.delivery.tracking.domain.model.Delivery;
import br.com.hiokdev.algadelivery.delivery.tracking.domain.repository.DeliveryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DeliveryCheckpointService {

    private final DeliveryRepository deliveryRepository;

    public void place(UUID deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(DomainException::new);
        delivery.place();
        deliveryRepository.saveAndFlush(delivery);
    }

    public void pickUp(UUID deliveryId, UUID courierId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(DomainException::new);
        delivery.pickUp(courierId);
        deliveryRepository.saveAndFlush(delivery);
    }

    public void complete(UUID deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(DomainException::new);
        delivery.markAsDelivered();
        deliveryRepository.saveAndFlush(delivery);
    }

}
