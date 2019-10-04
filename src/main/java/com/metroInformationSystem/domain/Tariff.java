package com.metroInformationSystem.domain;
import org.hibernate.annotations.Type;
import org.postgresql.util.PGInterval;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false)
    private int cost;

    @Type(type = "com.metroInformationSystem.types.PostgreSQLIntervalType")
    private PGInterval validity;
    private int metroRides;
    private int busRides;
    private int TramRide;
    private int TrolleybusRide;

    @Column(nullable = false)
    private Time timeout;


    public Tariff() {
    }

    public Tariff(String name, int cost, PGInterval validity, Integer metroRides,
                  Integer busRides, Integer tramRide, Integer trolleybusRide, Time timeout) {
        this.name = name;
        this.cost = cost;
        this.validity = validity;
        this.metroRides = metroRides;
        this.busRides = busRides;
        TramRide = tramRide;
        TrolleybusRide = trolleybusRide;
        this.timeout = timeout;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public PGInterval getValidity() {
        return validity;
    }

    public int getMetroRides() {
        return metroRides;
    }

    public int getBusRides() {
        return busRides;
    }

    public int getTramRide() {
        return TramRide;
    }

    public int getTrolleybusRide() {
        return TrolleybusRide;
    }

    public Time getTimeout() {
        return timeout;
    }
}
