package br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.client;

import br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.dto.CourierPayoutCalculationInput;
import br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.dto.CourierPayoutResultModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/v1/couriers")
public interface CourierAPIClient {

    @PostExchange("/payout-calculation")
    CourierPayoutResultModel payoutCalculation(@RequestBody CourierPayoutCalculationInput input);

}
