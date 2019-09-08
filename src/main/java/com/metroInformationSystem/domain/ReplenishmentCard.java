package com.metroInformationSystem.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class ReplenishmentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(nullable = false)
    private TravelCard card;

    @Column(nullable = false)
    private long amount;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ReplenishmentType type;

    public ReplenishmentCard() {
    }

    public ReplenishmentCard(TravelCard card, long amount, ReplenishmentType type) {
        this.card = card;
        this.amount = amount;
        this.date = new Date(new java.util.Date().getTime());
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public TravelCard getCard() {
        return card;
    }

    public long getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public ReplenishmentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ReplenishmentCard{" +
                "id=" + id +
                ", card=" + card +
                ", amount=" + amount +
                ", date=" + date +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReplenishmentCard)) return false;
        ReplenishmentCard that = (ReplenishmentCard) o;
        return id == that.id &&
                amount == that.amount &&
                card.equals(that.card) &&
                date.equals(that.date) &&
                type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, card, amount, date, type);
    }
}
