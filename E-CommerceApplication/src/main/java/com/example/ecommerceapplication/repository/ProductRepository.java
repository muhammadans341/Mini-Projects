package com.example.ecommerceapplication.repository;

import com.example.ecommerceapplication.model.Category;
import com.example.ecommerceapplication.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
