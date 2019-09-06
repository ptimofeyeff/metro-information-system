package com.metroInformationSystem.domain;

import javax.persistence.*;

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
}
