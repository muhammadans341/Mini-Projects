package com.example.ecommerceapplication.repository;

import com.example.ecommerceapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
