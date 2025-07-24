package br.com.hiokdev.algadelivery.courier.management.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourierInput {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

}
