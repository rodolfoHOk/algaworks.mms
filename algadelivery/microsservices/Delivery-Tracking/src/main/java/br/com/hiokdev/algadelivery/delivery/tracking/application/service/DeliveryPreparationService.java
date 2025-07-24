package br.com.hiokdev.algadelivery.delivery.tracking.application.service;

import br.com.hiokdev.algadelivery.delivery.tracking.application.dto.DeliveryEstimate;
import br.com.hiokdev.algadelivery.delivery.tracking.application.dto.DeliveryInput;
import br.com.hiokdev.algadelivery.delivery.tracking.application.dto.ItemInput;
import br.com.hiokdev.algadelivery.delivery.tracking.domain.exception.DomainException;
import br.com.hiokdev.algadelivery.delivery.tracking.domain.model.ContactPoint;
import br.com.hiokdev.algadelivery.delivery.tracking.domain.model.Delivery;
import br.com.hiokdev.algadelivery.delivery.tracking.domain.repository.DeliveryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryPreparationService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryTimeEstimationService deliveryTimeEstimationService;
    private final CourierPayoutCalculationService courierPayoutCalculationService;

    @Value("${app.distance-fee-factor}")
    private String distanceFeeFactor;

    @Transactional
    public Delivery draft(DeliveryInput input) {
        Delivery delivery = Delivery.draft();
        handlePreparation(input, delivery);
        return deliveryRepository.saveAndFlush(delivery);
    }

    @Transactional
    public Delivery edit(UUID deliveryId, DeliveryInput input) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(DomainException::new);
        delivery.removeItems();
        handlePreparation(input, delivery);
        return deliveryRepository.saveAndFlush(delivery);
    }

    private void handlePreparation(DeliveryInput input, Delivery delivery) {
        ContactPoint sender = input.getSender().toDomain();
        ContactPoint recipient = input.getRecipient().toDomain();
        DeliveryEstimate estimate = deliveryTimeEstimationService.estimate(sender, recipient);

        BigDecimal calculatedPayout = courierPayoutCalculationService.calculatePayout(estimate.getDistanceInKm());
        BigDecimal distanceFee = calculateDistanceFee(estimate.getDistanceInKm());

        Delivery.PreparationDetails preparationDetails = Delivery.PreparationDetails.builder()
                .recipient(recipient)
                .sender(sender)
                .expectedDeliveryTime(estimate.getEstimatedTime())
                .courierPayout(calculatedPayout)
                .distanceFee(distanceFee)
                .build();

        delivery.editPreparationDetails(preparationDetails);

        for (ItemInput itemInput : input.getItems()) {
            delivery.addItem(itemInput.getName(), itemInput.getQuantity());
        }
    }

    private BigDecimal calculateDistanceFee(Double distanceInKm) {
        return new BigDecimal(distanceFeeFactor)
                .multiply(new BigDecimal(distanceInKm))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

}
