package org.example.customer.service;

import org.example.clients.fraud.FraudClient;
import org.example.customer.entitiy.CustomerEntity;
import org.example.customer.mapper.CustomerMapper;
import org.example.customer.model.requests.Customer;
import org.example.clients.fraud.FraudCheckResponse;
import org.example.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    private final FraudClient fraudClient;
    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository, RestTemplate restTemplate, FraudClient fraudClient){
        this.customerRepository=customerRepository;
        this.customerMapper=customerMapper;
        this.restTemplate = restTemplate;
        this.fraudClient = fraudClient;
    }
    public void registerCustomer(Customer customer){
        CustomerEntity entity = customerMapper.customerToCustomerEntity(customer);
        CustomerEntity result = customerRepository.saveAndFlush(entity);

        FraudCheckResponse fraudCheckResponse=fraudClient.isCustomerFraudster(entity.getId());
        if(fraudCheckResponse!=null && fraudCheckResponse.isFraudster()){
            throw new IllegalStateException();
        }

    }
}
