package com.metroInformationSystem.utils;

import com.metroInformationSystem.domain.GroundTransportation;
import com.metroInformationSystem.domain.Passenger;
import com.metroInformationSystem.domain.PaymentLocation;
import com.metroInformationSystem.domain.enums.CheckerPayment;
import com.metroInformationSystem.domain.enums.Transport;
import com.metroInformationSystem.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {


    private PassengerRepo passengerRepo;
    private CheckerPaymentRepo checkerPaymentRepo;
    private GroundTransportationRepo groundTransportationRepo;


    public Seeder(PassengerRepo passengerRepo, CheckerPaymentRepo checkerPaymentRepo, GroundTransportationRepo groundTransportationRepo) {
        this.passengerRepo = passengerRepo;
        this.checkerPaymentRepo = checkerPaymentRepo;
        this.groundTransportationRepo = groundTransportationRepo;

    }

    @Override
    public void run(String... args) {
        Passenger passenger1 = new Passenger("Тимофеев", "Павел", "Анатольевич");
        Passenger passenger2 = new Passenger("Полищук", "Евгений", "Александрович");

        passengerRepo.save(passenger1);
        passengerRepo.save(passenger2);

        PaymentLocation paymentLocation1 = new PaymentLocation(CheckerPayment.TURNSTILE);
        PaymentLocation paymentLocation2 = new PaymentLocation(CheckerPayment.VALIDATOR);

        checkerPaymentRepo.save(paymentLocation1);
        checkerPaymentRepo.save(paymentLocation2);


        GroundTransportation groundTransportation1 =
                new GroundTransportation(paymentLocation1, Transport.BUS, "30" );
        GroundTransportation groundTransportation2 =
                new GroundTransportation(paymentLocation2, Transport.TRAM, "64");

        groundTransportationRepo.save(groundTransportation1);
        groundTransportationRepo.save(groundTransportation2);



    }
}
