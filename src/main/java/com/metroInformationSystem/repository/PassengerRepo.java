package com.metroInformationSystem.repository;

import com.metroInformationSystem.domain.Passenger;
import org.springframework.data.repository.CrudRepository;

public interface PassengerRepo extends CrudRepository<Passenger, Long> {
    Passenger findByName(String name);
    Passenger findBySoname(String soName);
}
