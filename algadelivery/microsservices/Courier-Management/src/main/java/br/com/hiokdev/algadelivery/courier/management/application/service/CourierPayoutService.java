package br.com.hiokdev.algadelivery.courier.management.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CourierPayoutService {

    @Value("${app.courier-payout-factor}")
    private String courierPayoutFactor;

    public BigDecimal calculate(Double distanceInKm) {
        return new BigDecimal(courierPayoutFactor)
                .multiply(new BigDecimal(distanceInKm))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

}
