package br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CourierPayoutCalculationInput {

    private Double distanceInKm;

}
