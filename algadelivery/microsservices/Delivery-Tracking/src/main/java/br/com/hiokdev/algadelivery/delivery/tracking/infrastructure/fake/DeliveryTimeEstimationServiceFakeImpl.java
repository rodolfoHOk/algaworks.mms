package br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.fake;

import br.com.hiokdev.algadelivery.delivery.tracking.application.dto.DeliveryEstimate;
import br.com.hiokdev.algadelivery.delivery.tracking.application.service.DeliveryTimeEstimationService;
import br.com.hiokdev.algadelivery.delivery.tracking.domain.model.ContactPoint;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class DeliveryTimeEstimationServiceFakeImpl implements DeliveryTimeEstimationService {

    @Override
    public DeliveryEstimate estimate(ContactPoint sender, ContactPoint receiver) {
        return new DeliveryEstimate(Duration.ofHours(3), 3.1);
    }

}
