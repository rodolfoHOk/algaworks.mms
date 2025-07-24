package br.com.hiokdev.algadelivery.delivery.tracking.application.service;

import java.math.BigDecimal;

public interface CourierPayoutCalculationService {

    BigDecimal calculatePayout(Double distanceInKm);

}
