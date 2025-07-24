package br.com.hiokdev.algadelivery.delivery.traking.domain.model;

import br.com.hiokdev.algadelivery.delivery.traking.domain.exception.DomainException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Delivery {

    @EqualsAndHashCode.Include
    private UUID id;

    private UUID courierId;

    private DeliveryStatus status;

    private OffsetDateTime placedAt;
    private OffsetDateTime assignedAt;
    private OffsetDateTime expectedDeliveryAt;
    private OffsetDateTime fulfilledAt;

    private BigDecimal distanceFee;
    private BigDecimal courierPayout;
    private BigDecimal totalCost;

    private Integer totalItems;

    private List<Item> items = new ArrayList<>();

    private ContactPoint sender;
    private ContactPoint recipient;

    public static Delivery draft() {
        Delivery delivery = new Delivery();
        delivery.setId(UUID.randomUUID());
        delivery.setStatus(DeliveryStatus.DRAFT);
        delivery.setTotalItems(0);
        delivery.setDistanceFee(BigDecimal.ZERO);
        delivery.setCourierPayout(BigDecimal.ZERO);
        delivery.setTotalCost(BigDecimal.ZERO);
        return delivery;
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    public UUID addItem(String name, Integer quantity) {
        Item item = Item.brandNew(name, quantity);
        this.items.add(item);
        calculateTotalItems();
        return item.getId();
    }

    public void removeItem(UUID itemId) {
        this.items.removeIf(item -> item.getId().equals(itemId));
        calculateTotalItems();
    }

    public void changeItemQuantity(UUID itemId, Integer quantity) {
        Item item = items.stream().filter(i -> i.getId().equals(itemId)).findFirst().orElseThrow();
        item.setQuantity(quantity);
        calculateTotalItems();
    }

    public void removeItems() {
        this.items.clear();
        calculateTotalItems();
    }

    public void editPreparationDetails(PreparationDetails preparationDetails) {
        verifyIfCanBeEdited();
        this.setSender(preparationDetails.getSender());
        this.setRecipient(preparationDetails.getRecipient());
        this.setDistanceFee(preparationDetails.getDistanceFee());
        this.setCourierPayout(preparationDetails.getCourierPayout());
        this.setExpectedDeliveryAt(OffsetDateTime.now().plus(preparationDetails.getExpectedDeliveryTime()));
        this.setTotalCost(this.getDistanceFee().add(preparationDetails.getCourierPayout()));
    }

    public void place() {
        verifyIfCanBePlaced();
        this.changeStatusTo(DeliveryStatus.WAITING_FOR_COURIER);
        this.setPlacedAt(OffsetDateTime.now());
    }

    public void pickUp(UUID courierId) {
        this.setCourierId(courierId);
        this.changeStatusTo(DeliveryStatus.IN_TRANSIT);
        this.setAssignedAt(OffsetDateTime.now());
    }

    public void markAsDelivered() {
        this.changeStatusTo(DeliveryStatus.DELIVERED);
        this.setFulfilledAt(OffsetDateTime.now());
    }

    private void calculateTotalItems() {
        int totalItems = getItems().stream().mapToInt(Item::getQuantity).sum();
        this.setTotalItems(totalItems);
    }

    private void verifyIfCanBePlaced() {
        if (!isFilled()) {
            throw new DomainException();
        }
    }

    private boolean isFilled() {
        return this.getSender() != null && this.getRecipient() != null && this.getTotalCost() != null;
    }

    private void verifyIfCanBeEdited() {
        if (!this.getStatus().equals(DeliveryStatus.DRAFT)) {
            throw new DomainException();
        }
    }

    private void changeStatusTo(DeliveryStatus newStatus) {
        if (newStatus != null && this.getStatus().canNotChangeTo(newStatus)) {
            throw new DomainException("Invalid status transition from " + this.getStatus() + " to " + newStatus);
        }
        this.setStatus(newStatus);
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class PreparationDetails {

        private ContactPoint sender;
        private ContactPoint recipient;
        private BigDecimal distanceFee;
        private BigDecimal courierPayout;
        private Duration expectedDeliveryTime;

    }

}
