package org.microservices.customer.mapper;

import org.microservices.customer.entitiy.CustomerEntity;
import org.microservices.customer.model.requests.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerEntity customerToCustomerEntity(Customer customer);
    Customer customerEntityToCustomer(CustomerEntity customerEntity);
}
