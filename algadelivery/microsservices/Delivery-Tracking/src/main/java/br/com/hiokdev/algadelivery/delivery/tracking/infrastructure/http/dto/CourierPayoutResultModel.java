package br.com.hiokdev.algadelivery.delivery.tracking.infrastructure.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class CourierPayoutResultModel {

    private BigDecimal payoutFee;

}
