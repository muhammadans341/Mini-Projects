package com.example.manyToMany.Repositories;


import com.example.manyToMany.Entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    //public findUserById(String id)
}
