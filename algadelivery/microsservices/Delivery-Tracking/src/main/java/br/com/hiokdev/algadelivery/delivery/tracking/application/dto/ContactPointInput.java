package br.com.hiokdev.algadelivery.delivery.tracking.application.dto;

import br.com.hiokdev.algadelivery.delivery.tracking.domain.model.ContactPoint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactPointInput {

    @NotBlank
    private String zipCode;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    private String complement;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    public ContactPoint toDomain() {
        return ContactPoint.builder()
                .phone(this.getPhone())
                .name(this.getName())
                .complement(this.getComplement())
                .number(this.getNumber())
                .zipCode(this.getZipCode())
                .street(this.getStreet())
                .build();
    }

}
