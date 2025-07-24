package br.com.hiokdev.algadelivery.delivery.tracking.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryStatusTest {

    @Test
    void draftCanChangeToWaitingForCourier() {
        assertTrue(DeliveryStatus.DRAFT.canChangeTo(DeliveryStatus.WAITING_FOR_COURIER));
    }

    @Test
    void draftCanNotChangeToInTransit() {
        assertTrue(DeliveryStatus.DRAFT.canNotChangeTo(DeliveryStatus.IN_TRANSIT));
    }

}
