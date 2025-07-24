package br.com.hiokdev.algadelivery.delivery.tracking.domain.repository;

import br.com.hiokdev.algadelivery.delivery.tracking.domain.model.ContactPoint;
import br.com.hiokdev.algadelivery.delivery.tracking.domain.model.Delivery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeliveryRepositoryTest {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    void shouldPersist() {
        Delivery delivery = Delivery.draft();
        delivery.editPreparationDetails(createValidPreparationDetails());
        delivery.addItem("Computador", 2);
        delivery.addItem("Notebook", 2);

        deliveryRepository.saveAndFlush(delivery);

        Delivery persistedDelivery = deliveryRepository.findById(delivery.getId()).orElseThrow();
        assertEquals(2, persistedDelivery.getItems().size());
    }


    private Delivery.PreparationDetails createValidPreparationDetails() {
        ContactPoint sender = ContactPoint.builder()
                .zipCode("01001-100")
                .street("Av São Paulo")
                .number("123")
                .complement("Sala 401")
                .name("João Silva")
                .phone("(11) 95555-1234")
                .build();

        ContactPoint recipient = ContactPoint.builder()
                .zipCode("04002-101")
                .street("Av São João")
                .number("456")
                .complement("")
                .name("Maria Souza")
                .phone("(11) 96666-5678")
                .build();

        return Delivery.PreparationDetails.builder()
                .sender(sender)
                .recipient(recipient)
                .distanceFee(new BigDecimal("15.00"))
                .courierPayout(new BigDecimal("5.00"))
                .expectedDeliveryTime(Duration.ofMinutes(40))
                .build();
    }

}
