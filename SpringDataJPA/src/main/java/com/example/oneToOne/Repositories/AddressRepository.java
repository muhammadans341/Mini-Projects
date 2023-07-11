package com.example.oneToOne.Repositories;


import com.example.oneToOne.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    //public findUserById(String id)
}
