package br.com.hiokdev.algadelivery.delivery.traking.domain.model;

import br.com.hiokdev.algadelivery.delivery.traking.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {

    @Test
    void shouldChangeToPlaced() {
        Delivery delivery = Delivery.draft();
        delivery.editPreparationDetails(createValidPreparationDetails());
        delivery.place();

        assertEquals(DeliveryStatus.WAITING_FOR_COURIER, delivery.getStatus());
        assertNotNull(delivery.getPlacedAt());
    }

    @Test
    void shouldNotPlaced() {
        Delivery delivery = Delivery.draft();

        assertThrows(DomainException.class, delivery::place);

        assertEquals(DeliveryStatus.DRAFT, delivery.getStatus());
        assertNull(delivery.getPlacedAt());
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
