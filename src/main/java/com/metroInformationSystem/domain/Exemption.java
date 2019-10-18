package com.metroInformationSystem.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Exemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "exemptions", fetch = FetchType.EAGER)
    private Collection<Exempt> exempts;

    private boolean isIndefinitely;


    public Exemption() {
    }

    public String getName() {
        return name;
    }

    public Collection<Exempt> getExempts() {
        return exempts;
    }

    public Exemption(String name, Collection<Exempt> exempts, boolean isIndefinitely) {
        this.name = name;
        this.exempts = exempts;
        this.isIndefinitely = isIndefinitely;
    }

    public void setExempts(Collection<Exempt> exempts) {
        this.exempts = exempts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exemption)) return false;
        Exemption exemption = (Exemption) o;
        return isIndefinitely == exemption.isIndefinitely &&
                Objects.equals(id, exemption.id) &&
                Objects.equals(name, exemption.name) &&
                Objects.equals(exempts, exemption.exempts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, exempts, isIndefinitely);
    }
}
