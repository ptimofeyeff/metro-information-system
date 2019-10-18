package com.metroInformationSystem.repository;

import com.metroInformationSystem.domain.Exempt;
import com.metroInformationSystem.domain.Passenger;
import org.springframework.data.repository.CrudRepository;

public interface ExemptRepo extends CrudRepository<Exempt, Long> {
    Exempt findByPassenger(Passenger passenger);
}
