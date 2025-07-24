package br.com.hiokdev.algadelivery.courier.management.application.service;

import br.com.hiokdev.algadelivery.courier.management.application.dto.CourierInput;
import br.com.hiokdev.algadelivery.courier.management.domain.exception.DomainException;
import br.com.hiokdev.algadelivery.courier.management.domain.model.Courier;
import br.com.hiokdev.algadelivery.courier.management.domain.repository.CourierRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CourierRegistrationService {

    private final CourierRepository courierRepository;

    public Courier create(@Valid CourierInput input) {
        Courier courier = Courier.brandNew(input.getName(), input.getPhone());
        return courierRepository.saveAndFlush(courier);
    }

    public Courier update(UUID courierId, @Valid CourierInput input) {
        Courier courier = courierRepository.findById(courierId).orElseThrow(DomainException::new);
        courier.setPhone(input.getPhone());
        courier.setName(input.getName());
        return courierRepository.saveAndFlush(courier);
    }

}
