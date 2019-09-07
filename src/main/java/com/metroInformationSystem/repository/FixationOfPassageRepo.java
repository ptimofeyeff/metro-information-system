package com.metroInformationSystem.repository;

import com.metroInformationSystem.domain.FixationOfPassage;
import com.metroInformationSystem.domain.TravelCard;
import org.springframework.data.repository.CrudRepository;

public interface FixationOfPassageRepo extends CrudRepository<FixationOfPassage, Long> {
    Iterable<FixationOfPassage> findByTravelCard(TravelCard travelCard);
}
