package com.metroInformationSystem.repository;

import com.metroInformationSystem.domain.Station;
import org.springframework.data.repository.CrudRepository;

public interface StationRepo extends CrudRepository<Station, Long> {
     Station findByName(String name);
}
