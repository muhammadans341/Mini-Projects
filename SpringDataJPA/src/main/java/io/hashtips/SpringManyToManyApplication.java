package io.hashtips;

import com.example.dataJPA.repository.*;
import com.example.dataJPA.service.StudentService;
import io.hashtips.domain.Customer;
import io.hashtips.domain.Sale;
import io.hashtips.domain.Vehicle;
import io.hashtips.repository.CustomerRepository;
import io.hashtips.repository.VehicleRepository;
import io.hashtips.service.VehicleService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;

@SpringBootApplication
public class SpringManyToManyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringManyToManyApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository, VehicleRepository vehicleRepository, VehicleService vehicleService) {
        return args -> {
            Customer newCustomer = new Customer();
            newCustomer.setCustomerName("Customer 1");
            Vehicle v = new Vehicle();
            v.setVehicleName("Audi");
            vehicleRepository.save(v);

            Vehicle vehicle = vehicleService.findVehicleById(1L);

            Sale newSale = new Sale();
                        newSale.setVehicle(vehicle);
                        newSale.setCustomer(newCustomer);
                        newSale.setDiscount(5);
            newCustomer.getSales().add(newSale);

            //customerRepository.save(newCustomer);


//            Customer c = new Customer();
//            newCustomer.getSales().addAll((customer.getSales()
//                    .stream()
//                    .map(sale -> {
//                        Vehicle vehicle = vehicleService.findVehicleById(1L);
//                        Sale newSale = new Sale();
//                        newSale.setVehicle(vehicle);
//                        newSale.setCustomer(newCustomer);
//                        newSale.setDiscount(sale.getDiscount());
//                        return newSale;
//                    })
//                    .collect(Collectors.toList())
//            ));

        };
    }
}
