package com.example.ecommerceapplication.repository;

import com.example.ecommerceapplication.model.Category;
import com.example.ecommerceapplication.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CetagoryRepository extends JpaRepository<Category,Long> {
}
