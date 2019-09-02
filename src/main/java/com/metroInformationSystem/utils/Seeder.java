package com.metroInformationSystem.utils;

import com.metroInformationSystem.domain.Passenger;
import com.metroInformationSystem.domain.PaymentLocation;
import com.metroInformationSystem.domain.enums.CheckerPayment;
import com.metroInformationSystem.repository.CheckerPaymentRepo;
import com.metroInformationSystem.repository.PassengerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {


    private PassengerRepo passengerRepo;
    private CheckerPaymentRepo checkerPaymentRepo;

    public Seeder(PassengerRepo passengerRepo, CheckerPaymentRepo checkerPaymentRepo) {
        this.passengerRepo = passengerRepo;
        this.checkerPaymentRepo = checkerPaymentRepo;
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
    }
}
