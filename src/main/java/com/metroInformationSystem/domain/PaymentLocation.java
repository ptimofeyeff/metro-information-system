package com.metroInformationSystem.domain;

import com.metroInformationSystem.domain.enums.CheckerPayment;
import com.metroInformationSystem.utils.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Objects;

@Entity
@TypeDef(name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class)
public class PaymentLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(columnDefinition = "checker_payment", nullable = false)
    private CheckerPayment checkerPayment;


    public PaymentLocation(){}


    public PaymentLocation(CheckerPayment checkerPayment) {
        this.checkerPayment = checkerPayment;
    }

    public CheckerPayment getCheckerPayment() {
        return checkerPayment;
    }

    @Override
    public String toString() {
        return "PaymentLocation{" +
                "id=" + id +
                ", checkerPayment=" + checkerPayment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentLocation)) return false;
        PaymentLocation that = (PaymentLocation) o;
        return id == that.id &&
                checkerPayment == that.checkerPayment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkerPayment);
    }
}
