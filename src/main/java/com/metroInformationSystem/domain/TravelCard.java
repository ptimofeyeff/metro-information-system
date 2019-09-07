package com.metroInformationSystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class TravelCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public TravelCard() {
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TravelCard{" +
                "id=" + id +
                '}';
    }
}
