package com.metroInformationSystem;

import com.metroInformationSystem.domain.Passenger;
import com.metroInformationSystem.repository.PassengerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {


    private PassengerRepo passengerRepo;

    public Seeder(PassengerRepo passengerRepo) {
        this.passengerRepo = passengerRepo;
    }

    @Override
    public void run(String... args) {
        Passenger passenger1 = new Passenger("Тимофеев", "Павел", "Анатольевич");
        Passenger passenger2 = new Passenger("Полищук", "Евгений", "Александрович");

        passengerRepo.save(passenger1);
        passengerRepo.save(passenger2);


    }
}
