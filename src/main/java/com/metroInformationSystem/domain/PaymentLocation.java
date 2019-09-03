package com.metroInformationSystem.domain;

import com.metroInformationSystem.domain.enums.CheckerPayment;
import com.metroInformationSystem.utils.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

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
}
