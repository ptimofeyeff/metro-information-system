package com.metroInformationSystem.domain;

import com.metroInformationSystem.domain.enums.ReplenishmentMethod;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReplenishmentType)) return false;
        ReplenishmentType that = (ReplenishmentType) o;
        return id.equals(that.id) &&
                Objects.equals(station, that.station) &&
                method == that.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, station, method);
    }
}
