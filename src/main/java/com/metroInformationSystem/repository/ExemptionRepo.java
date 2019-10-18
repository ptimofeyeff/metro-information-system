package com.metroInformationSystem.repository;

import com.metroInformationSystem.domain.Exemption;
import org.springframework.data.repository.CrudRepository;

public interface ExemptionRepo extends CrudRepository<Exemption, Long> {
    Exemption findByName(String name);
}
