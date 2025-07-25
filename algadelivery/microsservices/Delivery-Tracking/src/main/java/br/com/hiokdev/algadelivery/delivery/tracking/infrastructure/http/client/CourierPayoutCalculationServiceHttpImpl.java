package br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.client;

import br.com.hiokdev.algadelivery.delivery.tracking.application.service.CourierPayoutCalculationService;
import br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.dto.CourierPayoutCalculationInput;
import br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.dto.CourierPayoutResultModel;
import br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.exception.BadGatewayException;
import br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.exception.GatewayTimeoutException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CourierPayoutCalculationServiceHttpImpl implements CourierPayoutCalculationService {

    private final CourierAPIClient courierAPIClient;

    @Override
    public BigDecimal calculatePayout(Double distanceInKm) {
        try {
            CourierPayoutResultModel courierPayoutResultModel = courierAPIClient
                    .payoutCalculation(new CourierPayoutCalculationInput(distanceInKm));
            return courierPayoutResultModel.getPayoutFee();
        } catch (ResourceAccessException e) {
            throw new GatewayTimeoutException(e);
        } catch (HttpServerErrorException | CallNotPermittedException | IllegalArgumentException e) {
            throw new BadGatewayException(e);
        }
    }

}
