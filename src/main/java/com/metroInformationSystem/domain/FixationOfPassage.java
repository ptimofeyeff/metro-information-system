package com.metroInformationSystem.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class FixationOfPassage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn
    private TravelCard travelCard;

    @ManyToOne
    @JoinColumn
    private PaymentLocation paymentLocation;

    @Column(nullable = false)
    private Date date;

    public FixationOfPassage(TravelCard travelCard, PaymentLocation paymentLocation, Date date) {
        this.travelCard = travelCard;
        this.paymentLocation = paymentLocation;
        this.date = date;
    }

    public FixationOfPassage() {
    }

    public long getId() {
        return id;
    }

    public TravelCard getTravelCard() {
        return travelCard;
    }

    public PaymentLocation getPaymentLocation() {
        return paymentLocation;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "FixationOfPassage{" +
                "id=" + id +
                ", travelCard=" + travelCard +
                ", paymentLocation=" + paymentLocation +
                ", date=" + date +
                '}';
    }
}
