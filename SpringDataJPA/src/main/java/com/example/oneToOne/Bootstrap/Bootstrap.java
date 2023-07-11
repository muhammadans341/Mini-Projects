package com.example.oneToOne.Bootstrap;


import com.example.oneToOne.Entity.Address;
import com.example.oneToOne.Entity.User;
import com.example.oneToOne.Repositories.AddressRepository;
import com.example.oneToOne.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private UserRepository userRepository;
    private AddressRepository addressRepository;
    public Bootstrap(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository=userRepository;
        this.addressRepository=addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        Address address = new Address();
        address.setAddress("Samnababd");

        User user= new User();
        user.setName("Nauman");
        user.setLastname("Arif");
        //address.setUser(user);
        user.setAddress(address);


        Address address2 = new Address();
        address2.setAddress("Samnababd  22");

        User user2= new User();
        user2.setName("Nauman");
        user2.setLastname("Nauman arif 22");
        user2.setAddress(address2);
        userRepository.save(user);
        userRepository.save(user2);

        Address result = addressRepository.findById(1L).get();
        User userResult = userRepository.findById(1L).get();
        //System.out.println(result);
        //System.out.println(userResult);
    }
}