package br.com.hiokdev.algadelivery.courier.management.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Courier {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Setter(AccessLevel.PUBLIC)
    private String name;

    @Setter(AccessLevel.PUBLIC)
    private String phone;

    private Integer fulfilledDeliveriesQuantity;

    private Integer pendingDeliveriesQuantity;

    private OffsetDateTime lastFulfilledDeliveryAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "courier")
    private List<AssignedDelivery> pendingDeliveries = new ArrayList<>();

    public static Courier brandNew(String name, String phone) {
        Courier courier = new Courier();
        courier.setId(UUID.randomUUID());
        courier.setName(name);
        courier.setPhone(phone);
        courier.setFulfilledDeliveriesQuantity(0);
        courier.setPendingDeliveriesQuantity(0);
        return courier;
    }

    public List<AssignedDelivery> getPendingDeliveries() {
        return Collections.unmodifiableList(this.getPendingDeliveries());
    }

    public void assign(UUID deliveryId) {
        this.pendingDeliveries.add(AssignedDelivery.pending(deliveryId, this));
        this.setPendingDeliveriesQuantity(this.getPendingDeliveriesQuantity() + 1);
    }

    public void fulfill(UUID deliveryId) {
        AssignedDelivery assignedDelivery = this.pendingDeliveries.stream()
                .filter(pd -> pd.getId().equals(deliveryId)).findFirst().orElseThrow();
        this.pendingDeliveries.remove(assignedDelivery);
        this.setPendingDeliveriesQuantity(this.getPendingDeliveriesQuantity() - 1);
        this.setFulfilledDeliveriesQuantity(this.getFulfilledDeliveriesQuantity() + 1);
        this.setLastFulfilledDeliveryAt(OffsetDateTime.now());
    }

}
