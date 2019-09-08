package com.metroInformationSystem.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn
    private PaymentLocation paymentLocation;

    @Column(nullable = false, length = 100)
    private String name;


    public Station() {
    }

    public Station(PaymentLocation paymentLocation, String name) {
        this.paymentLocation = paymentLocation;
        this.name = name;
    }

    public PaymentLocation getPaymentLocation() {
        return paymentLocation;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;
        Station station = (Station) o;
        return id == station.id &&
                paymentLocation.equals(station.paymentLocation) &&
                name.equals(station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentLocation, name);
    }
}
