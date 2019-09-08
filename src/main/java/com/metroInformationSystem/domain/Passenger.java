package com.metroInformationSystem.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String soname;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(length = 50)
    private String patronymic;

    public Passenger() {
    }

    public Passenger(String soname, String name, String patronymic) {
        this.soname = soname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public String getSoname() {
        return soname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;
        Passenger passenger = (Passenger) o;
        return id == passenger.id &&
                soname.equals(passenger.soname) &&
                name.equals(passenger.name) &&
                Objects.equals(patronymic, passenger.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, soname, name, patronymic);
    }
}
