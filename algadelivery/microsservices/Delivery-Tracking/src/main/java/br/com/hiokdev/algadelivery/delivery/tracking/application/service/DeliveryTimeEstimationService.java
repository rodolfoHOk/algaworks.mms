package br.com.hiokdev.algadelivery.delivery.tracking.application.service;

import br.com.hiokdev.algadelivery.delivery.tracking.application.dto.DeliveryEstimate;
import br.com.hiokdev.algadelivery.delivery.tracking.domain.model.ContactPoint;

public interface DeliveryTimeEstimationService {

    DeliveryEstimate estimate(ContactPoint sender, ContactPoint receiver);

}
