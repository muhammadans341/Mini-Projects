package com.example.oneToMany.Repositories;


import com.example.oneToMany.Entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    //public findUserById(String id)
}
