package org.microservices.fraud.repository;

import org.microservices.fraud.entity.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckRepository extends JpaRepository<FraudCheckHistory,Long> {
}
