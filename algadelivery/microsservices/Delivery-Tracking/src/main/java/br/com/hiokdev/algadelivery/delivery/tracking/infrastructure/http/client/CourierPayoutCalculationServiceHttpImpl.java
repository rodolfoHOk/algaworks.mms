package br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.client;

import br.com.hiokdev.algadelivery.delivery.tracking.application.service.CourierPayoutCalculationService;
import br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.dto.CourierPayoutCalculationInput;
import br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.dto.CourierPayoutResultModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CourierPayoutCalculationServiceHttpImpl implements CourierPayoutCalculationService {

    private final CourierAPIClient courierAPIClient;

    @Override
    public BigDecimal calculatePayout(Double distanceInKm) {
        CourierPayoutResultModel courierPayoutResultModel = courierAPIClient
                .payoutCalculation(new CourierPayoutCalculationInput(distanceInKm));
        return courierPayoutResultModel.getPayoutFee();
    }

}
