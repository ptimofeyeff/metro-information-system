package com.metroInformationSystem.repository;

import com.metroInformationSystem.domain.Tariff;
import org.springframework.data.repository.CrudRepository;

public interface TariffRepo extends CrudRepository<Tariff, Long> {
    Tariff findByName(String name);
}
