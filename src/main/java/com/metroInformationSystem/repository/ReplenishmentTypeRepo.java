package com.metroInformationSystem.repository;

import com.metroInformationSystem.domain.ReplenishmentType;
import com.metroInformationSystem.domain.enums.ReplenishmentMethod;
import org.springframework.data.repository.CrudRepository;

public interface ReplenishmentTypeRepo extends CrudRepository<ReplenishmentType, Long> {
    ReplenishmentType findByMethod(ReplenishmentMethod method);
}
