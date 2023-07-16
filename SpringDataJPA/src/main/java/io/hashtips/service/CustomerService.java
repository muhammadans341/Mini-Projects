package io.hashtips.service;

import io.hashtips.domain.Customer;
import io.hashtips.domain.Sale;
import io.hashtips.domain.Vehicle;
import io.hashtips.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleService vehicleService;

    public Customer saveCustomer(Customer customer) {
//        Customer newCustomer = new Customer();
//        newCustomer.setCustomerName(customer.getCustomerName());
//        newCustomer.getSales().addAll((customer.getSales()
//                .stream()
//                .map(sale -> {
//                    Vehicle vehicle = vehicleService.findVehicleById(1L);
//                    Sale newSale = new Sale();
//                    newSale.setVehicle(vehicle);
//                    newSale.setCustomer(newCustomer);
//                    newSale.setDiscount(sale.getDiscount());
//                    return newSale;
//                })
//                .collect(Collectors.toList())
//        ));
//        return customerRepository.save(newCustomer);

        Customer newCustomer = new Customer();
        newCustomer.setCustomerName("Customer 1");

        Vehicle vehicle = vehicleService.findVehicleById(1L);

        Sale newSale = new Sale();
        newSale.setVehicle(vehicle);
        newSale.setCustomer(newCustomer);
        newSale.setDiscount(5);
        newCustomer.getSales().add(newSale);

        return customerRepository.save(newCustomer);
    }
}
