package org.example.customer.mapper;

import org.example.customer.entitiy.CustomerEntity;
import org.example.customer.model.requests.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerEntity customerToCustomerEntity(Customer customer);
    Customer customerEntityToCustomer(CustomerEntity customerEntity);
}
