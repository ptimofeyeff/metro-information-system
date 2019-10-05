package com.metroInformationSystem.repository;

import com.metroInformationSystem.domain.Passenger;
import com.metroInformationSystem.domain.PassengerCard;
import com.metroInformationSystem.domain.TravelCard;
import org.springframework.data.repository.CrudRepository;

public interface PassengerCardRepo extends CrudRepository<PassengerCard, Long> {

    PassengerCard findByPassengerAndTravelCard(Passenger passenger, TravelCard card);
}
