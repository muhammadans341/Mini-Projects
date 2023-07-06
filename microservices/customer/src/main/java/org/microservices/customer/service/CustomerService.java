package org.microservices.customer.service;

import org.microservices.clients.fraud.FraudClient;
import org.microservices.clients.notification.NotificationClient;
import org.microservices.clients.notification.NotificationRequest;
import org.microservices.customer.entitiy.CustomerEntity;
import org.microservices.customer.mapper.CustomerMapper;
import org.microservices.customer.model.requests.Customer;
import org.microservices.clients.fraud.FraudCheckResponse;
import org.microservices.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    private final FraudClient fraudClient;

    private final NotificationClient notificationClient;
    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository, RestTemplate restTemplate, FraudClient fraudClient, NotificationClient notificationClient){
        this.customerRepository=customerRepository;
        this.customerMapper=customerMapper;
        this.restTemplate = restTemplate;
        this.fraudClient = fraudClient;
        this.notificationClient = notificationClient;
    }
    public void registerCustomer(Customer customer){
        CustomerEntity entity = customerMapper.customerToCustomerEntity(customer);
        CustomerEntity result = customerRepository.saveAndFlush(entity);

        FraudCheckResponse fraudCheckResponse=fraudClient.isCustomerFraudster(entity.getId());
        if(fraudCheckResponse!=null && fraudCheckResponse.isFraudster()){
            throw new IllegalStateException();
        }
        NotificationRequest request=NotificationRequest.builder()
                .toCustomerId(entity.getId())
                .toCustomerEmail(entity.getEmail())
                .message("You are registered successfully").build();
        notificationClient.sendNotification(request);
    }
}