package br.com.hiokdev.algadelivery.courier.management.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class CourierPayoutResultModel {

    private BigDecimal payoutFee;

}
