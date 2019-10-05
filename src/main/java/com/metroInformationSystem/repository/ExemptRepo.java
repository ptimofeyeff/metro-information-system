package com.metroInformationSystem.repository;

import com.metroInformationSystem.domain.Exempt;
import org.springframework.data.repository.CrudRepository;

public interface ExemptRepo extends CrudRepository<Exempt, Long> {
    Exempt findByKind(String kind);
}
