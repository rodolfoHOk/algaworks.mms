package br.com.hiokdev.algadelivery.courier.management.application.service;

import br.com.hiokdev.algadelivery.courier.management.domain.exception.DomainException;
import br.com.hiokdev.algadelivery.courier.management.domain.model.Courier;
import br.com.hiokdev.algadelivery.courier.management.domain.repository.CourierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CourierDeliveryService {

    private final CourierRepository courierRepository;

    public void assign(UUID deliveryId) {
        Courier courier = courierRepository.findTop1ByOrderByLastFulfilledDeliveryAtAsc()
                .orElseThrow(DomainException::new);
        courier.assign(deliveryId);
        courierRepository.saveAndFlush(courier);
        log.info("Courier {} assigned to delivery {}", courier.getId(), deliveryId);
    }

    public void fulfill(UUID deliveryId) {
        Courier courier = courierRepository.findByPendingDeliveries_id(deliveryId)
                .orElseThrow(DomainException::new);
        courier.fulfill(deliveryId);
        courierRepository.saveAndFlush(courier);
        log.info("Courier {} fulfilled the delivery {}", courier.getId(), deliveryId);
    }

}
