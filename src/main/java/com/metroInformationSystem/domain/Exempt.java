package com.metroInformationSystem.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Exempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn
    private Passenger passenger;

    @Column(nullable = false)
    private String kind;

    public Exempt(Passenger passenger, String kind) {
        this.passenger = passenger;
        this.kind = kind;
    }

    public Exempt() {
    }

    public long getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String getKind() {
        return kind;
    }

    @Override
    public String toString() {
        return "Exempt{" +
                "id=" + id +
                ", passenger=" + passenger +
                ", kind='" + kind + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exempt)) return false;
        Exempt exempt = (Exempt) o;
        return id == exempt.id &&
                passenger.equals(exempt.passenger) &&
                kind.equals(exempt.kind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passenger, kind);
    }
}
