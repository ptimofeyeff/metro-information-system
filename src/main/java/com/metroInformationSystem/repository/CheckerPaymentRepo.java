package com.metroInformationSystem.repository;

import com.metroInformationSystem.domain.PaymentLocation;
import org.springframework.data.repository.CrudRepository;

public interface CheckerPaymentRepo extends CrudRepository<PaymentLocation, Long> {
}
