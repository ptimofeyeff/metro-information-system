package com.metroInformationSystem.domain;

import com.metroInformationSystem.domain.enums.ReplenishmentMethod;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "replenishment_tp")
public class ReplenishmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Station station;

    @Enumerated(value = EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(columnDefinition = "replenishment_type", nullable = false)
    private ReplenishmentMethod method;

    public ReplenishmentType() {
    }

    public ReplenishmentType(Station station, ReplenishmentMethod method) {
        this.station = station;
        this.method = method;
    }

    public ReplenishmentType(ReplenishmentMethod method) {
        this.method = method;
    }

    public Long getId() {
        return id;
    }

    public Station getStation() {
        return station;
    }

    public ReplenishmentMethod getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return "ReplenishmentType{" +
                "id=" + id +
                ", station=" + station +
                ", method=" + method +
                '}';
    }
}
