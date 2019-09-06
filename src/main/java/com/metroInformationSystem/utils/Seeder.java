package com.metroInformationSystem.utils;

import com.metroInformationSystem.domain.*;
import com.metroInformationSystem.domain.enums.CheckerPayment;
import com.metroInformationSystem.domain.enums.Transport;
import com.metroInformationSystem.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*@Component
public class Seeder implements CommandLineRunner {


    private PassengerRepo passengerRepo;
    private CheckerPaymentRepo checkerPaymentRepo;
    private GroundTransportationRepo groundTransportationRepo;
    private TravelCardRepo travelCardRepo;
    private StationRepo stationRepo;


    public Seeder(PassengerRepo passengerRepo,
                  CheckerPaymentRepo checkerPaymentRepo,
                  GroundTransportationRepo groundTransportationRepo,
                  TravelCardRepo travelCardRepo,
                  StationRepo stationRepo) {
        this.passengerRepo = passengerRepo;
        this.checkerPaymentRepo = checkerPaymentRepo;
        this.groundTransportationRepo = groundTransportationRepo;
        this.travelCardRepo = travelCardRepo;
        this.stationRepo = stationRepo;
    }

    @Override
    public void run(String... args) {
        Passenger passenger1 = new Passenger("Тимофеев", "Павел", "Анатольевич");
        Passenger passenger2 = new Passenger("Полищук", "Евгений", "Александрович");

        passengerRepo.save(passenger1);
        passengerRepo.save(passenger2);

        PaymentLocation paymentLocation1 = new PaymentLocation(CheckerPayment.TURNSTILE);
        PaymentLocation paymentLocation2 = new PaymentLocation(CheckerPayment.VALIDATOR);

        PaymentLocation paymentLocation3 = new PaymentLocation(CheckerPayment.VALIDATOR);
        PaymentLocation paymentLocation4 = new PaymentLocation(CheckerPayment.TURNSTILE);

        checkerPaymentRepo.save(paymentLocation1);
        checkerPaymentRepo.save(paymentLocation2);

        TravelCard card1 = new TravelCard();
        TravelCard card2 = new TravelCard();

        travelCardRepo.save(card1);
        travelCardRepo.save(card2);

        // TODO: здесь наземному транспорту в качестве места оплаты передается турникет,
        // не забыть дописать запрещающий триггер
        GroundTransportation groundTransportation1 =
                new GroundTransportation(paymentLocation1, Transport.BUS, "30" );
        GroundTransportation groundTransportation2 =
                new GroundTransportation(paymentLocation2, Transport.TRAM, "64");

        groundTransportationRepo.save(groundTransportation1);

        groundTransportationRepo.save(groundTransportation2);

        Station station1 = new Station(paymentLocation3,"Ладожская" );
        // TODO: здесь станции в качестве места оплаты передается валидатор,
        // не забыть дописать запрещающий триггер
        Station station2 = new Station(paymentLocation4, "Новочеркасская");

        stationRepo.save(station1);
        stationRepo.save(station2);

    }
}
*/
