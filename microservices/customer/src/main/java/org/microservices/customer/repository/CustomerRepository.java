package org.microservices.customer.repository;

import org.microservices.customer.entitiy.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<CustomerEntity,Long> {
}
