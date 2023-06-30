package org.example.customer.service;

import lombok.RequiredArgsConstructor;
import org.example.customer.entitiy.CustomerEntity;
import org.example.customer.mapper.CustomerMapper;
import org.example.customer.model.requests.Customer;
import org.example.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerMapper customerMapper,CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
        this.customerMapper=customerMapper;
    }
    public void registerCustomer(Customer customer){
        CustomerEntity entity = customerMapper.customerToCustomerEntity(customer);
        CustomerEntity result = customerRepository.save(entity);
    }
}
