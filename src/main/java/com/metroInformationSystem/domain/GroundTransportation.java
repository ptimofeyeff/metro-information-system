package com.metroInformationSystem.domain;

import com.metroInformationSystem.domain.enums.Transport;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class GroundTransportation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @OneToOne
    @JoinColumn
    private PaymentLocation paymentLocation;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(columnDefinition = "transport", nullable = false)
    private Transport transport;

    @Column(length = 30, nullable = false)
    private String route;


    public GroundTransportation() {}

    public GroundTransportation(PaymentLocation paymentLocation, Transport transport, String route) {
        this.paymentLocation = paymentLocation;
        this.transport = transport;
        this.route = route;
    }

    public PaymentLocation getPaymentLocation() {
        return paymentLocation;
    }

    public Transport getTransport() {
        return transport;
    }

    public String getRoute() {
        return route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroundTransportation)) return false;
        GroundTransportation that = (GroundTransportation) o;
        return id == that.id &&
                paymentLocation.equals(that.paymentLocation) &&
                transport == that.transport &&
                route.equals(that.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentLocation, transport, route);
    }
}
