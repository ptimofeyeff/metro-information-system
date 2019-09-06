package com.metroInformationSystem.repository;

import com.metroInformationSystem.domain.GroundTransportation;
import com.metroInformationSystem.domain.enums.Transport;
import org.springframework.data.repository.CrudRepository;

public interface GroundTransportationRepo extends CrudRepository<GroundTransportation, Long> {
    GroundTransportation getByTransport(Transport transport);
}
