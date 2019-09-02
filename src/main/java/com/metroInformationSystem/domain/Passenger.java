package com.metroInformationSystem.domain;

import javax.persistence.*;

@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String soname;
    @Column(nullable = false)
    private String name;
    @Column(nullable = true)
    private String patronymic;

    public Passenger() {
    }

    public Passenger(String soname, String name, String patronymic) {
        this.soname = soname;
        this.name = name;
        this.patronymic = patronymic;
    }
}
