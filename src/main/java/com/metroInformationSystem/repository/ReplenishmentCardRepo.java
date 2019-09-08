package com.metroInformationSystem.repository;

import com.metroInformationSystem.domain.ReplenishmentCard;
import org.springframework.data.repository.CrudRepository;

public interface ReplenishmentCardRepo extends CrudRepository<ReplenishmentCard, Long> {
    ReplenishmentCard findByAmount(long amount);
}
