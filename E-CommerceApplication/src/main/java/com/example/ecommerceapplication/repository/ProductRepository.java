package com.example.ecommerceapplication.repository;

import com.example.ecommerceapplication.model.Category;
import com.example.ecommerceapplication.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByCategory(Category category, Pageable pageable);

    @Query(value = "SELECT * FROM product WHERE category_id = :categoryId ORDER BY product_id",
            countQuery = "SELECT count(*) FROM product WHERE category_id = :categoryId",
            nativeQuery = true)
    Page<Product> findByCategoryIdWithPagination(@Param("categoryId") Long categoryId,
                                                 Pageable pageable);
}
