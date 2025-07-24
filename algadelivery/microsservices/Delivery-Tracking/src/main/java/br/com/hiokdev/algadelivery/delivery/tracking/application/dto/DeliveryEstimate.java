package br.com.hiokdev.algadelivery.delivery.tracking.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor
public class DeliveryEstimate {

    private Duration estimatedTime;
    private Double distanceInKm;

}
