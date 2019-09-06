package com.metroInformationSystem.domain;

import javax.persistence.*;

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
}
