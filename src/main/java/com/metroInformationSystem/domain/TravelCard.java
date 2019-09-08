package com.metroInformationSystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelCard)) return false;
        TravelCard card = (TravelCard) o;
        return id == card.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
