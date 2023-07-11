package com.example.oneToOne.Repositories;


import com.example.oneToOne.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
