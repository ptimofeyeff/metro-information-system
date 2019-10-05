package com.metroInformationSystem.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"passenger_id", "travel_card_id"})})
public class PassengerCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PassengerCardId;

    @OneToOne
    @JoinColumn
    private Passenger passenger;

    @OneToOne
    @JoinColumn(unique = true)
    private TravelCard travelCard;

    @ManyToOne
    @JoinColumn
    private Tariff tariff;

    private LocalDateTime recharge;

    @Column(nullable = false)
    private LocalDateTime expiration;

    @Column(nullable = false)
    private int restOfBusTrips;

    @Column(nullable = false)
    private int restOfTramTrips;

    @Column(nullable = false)
    private int restOfTrolleybusTrips;

    @Column(nullable = false)
    private int restOfMetroTrips;

    private Integer restOfMoney;


    public PassengerCard(Passenger passenger, TravelCard card, Tariff tariff,
                         LocalDateTime recharge, LocalDateTime expiration) {
        this.passenger = passenger;
        this.travelCard = card;
        this.tariff = tariff;
        this.recharge = recharge;
        this.expiration = expiration;
        restOfBusTrips = tariff.getBusRides();
        restOfTramTrips = tariff.getTramRide();
        restOfTrolleybusTrips = tariff.getTrolleybusRide();
        restOfMetroTrips = tariff.getMetroRides();
        restOfMoney = null;
    }

    public PassengerCard() {
    }


    public Long getPassengerCardId() {
        return PassengerCardId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public TravelCard getTravelCard() {
        return travelCard;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public LocalDateTime getRecharge() {
        return recharge;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public int getRestOfBusTrips() {
        return restOfBusTrips;
    }

    public int getRestOfTramTrips() {
        return restOfTramTrips;
    }

    public int getRestOfTrolleybusTrips() {
        return restOfTrolleybusTrips;
    }

    public int getRestOfMetroTrips() {
        return restOfMetroTrips;
    }

    public Integer getRestOfMoney() {
        return restOfMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassengerCard)) return false;
        PassengerCard that = (PassengerCard) o;
        return restOfBusTrips == that.restOfBusTrips &&
                restOfTramTrips == that.restOfTramTrips &&
                restOfTrolleybusTrips == that.restOfTrolleybusTrips &&
                restOfMetroTrips == that.restOfMetroTrips &&
                PassengerCardId.equals(that.PassengerCardId) &&
                Objects.equals(passenger, that.passenger) &&
                Objects.equals(travelCard, that.travelCard) &&
                Objects.equals(tariff, that.tariff) &&
                Objects.equals(recharge, that.recharge) &&
                Objects.equals(expiration, that.expiration) &&
                Objects.equals(restOfMoney, that.restOfMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PassengerCardId, passenger, travelCard, tariff, recharge, expiration, restOfBusTrips, restOfTramTrips, restOfTrolleybusTrips, restOfMetroTrips, restOfMoney);
    }
}

