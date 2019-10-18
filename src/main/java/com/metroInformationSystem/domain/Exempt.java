package com.metroInformationSystem.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Exempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn
    private Passenger passenger;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "exempt_exemption", joinColumns = @JoinColumn(name = "exempt_id"),
            inverseJoinColumns = @JoinColumn(name = "exemption_id")
    )
    private Collection<Exemption> exemptions;

    @Column(nullable = false)
    private LocalDateTime receipt;

    @Column(nullable = false)
    private LocalDateTime expiration;



    public Exempt() {
    }

    public Exempt(Passenger passenger, Collection<Exemption> exemptions, LocalDateTime receipt, LocalDateTime expiration) {
        this.passenger = passenger;
        this.exemptions = exemptions;
        this.receipt = receipt;
        this.expiration = expiration;
    }

    public Collection<Exemption> getExemptions() {
        return exemptions;
    }

    public void setExemptions(Collection<Exemption> exemptions) {
        this.exemptions = exemptions;
    }

    public LocalDateTime getReceipt() {
        return receipt;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exempt)) return false;
        Exempt exempt = (Exempt) o;
        return id == exempt.id &&
                Objects.equals(passenger, exempt.passenger) &&
                Objects.equals(exemptions, exempt.exemptions) &&
                Objects.equals(receipt, exempt.receipt) &&
                Objects.equals(expiration, exempt.expiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passenger, exemptions, receipt, expiration);
    }

    public long getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }


}
