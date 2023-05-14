package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.Exception.ProductNotFoundException;
import com.example.ecommerceapplication.Util;
import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.model.Category;
import com.example.ecommerceapplication.model.Product;
import com.example.ecommerceapplication.repository.CetagoryRepository;
import com.example.ecommerceapplication.repository.ProductRepository;
import com.example.ecommerceapplication.response.ProductsResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    ProductRepository productRepository;
    CetagoryRepository categoryRepository;
    public ProductService(ProductRepository productRepository,CetagoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }
    public ProductDTO addProduct(ProductDTO productDTO){

        Product product = Util.toEntity(productDTO);
        product = productRepository.save(product);
        return Util.toDTO(product);
    }

    public ProductDTO getProduct(Long productId){
       Optional<Product> product = productRepository.findById(productId);
        return product.map(Util::toDTO).orElse(null);
    }

    public void deleteProductById(Long productId){
        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found for deletion"));
        productRepository.deleteById(productId);
    }

    public ProductDTO updateProduct(Long productId, ProductDTO newProductDTO){
        Optional<Product> existingProductOptional = productRepository.findById(productId);
        if(existingProductOptional.isPresent()){
            Product existingProduct = existingProductOptional.get();
            BeanUtils.copyProperties(Util.toEntity(newProductDTO),existingProduct);
            existingProduct.setId(productId);
            Product savedProduct = productRepository.save(existingProduct);
            return Util.toDTO(savedProduct);
        }
       return null;
    }

    public ProductsResponse getAllProducts(Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Product> productsPages = productRepository.findAll(paging);
        List<ProductDTO> productsDTO =  productsPages.stream().map(Util::toDTO).collect(Collectors.toList());

        return ProductsResponse.builder().products(productsDTO)
                .pageNumber(productsPages.getNumber())
                .totalPages(productsPages.getTotalPages())
                .pageSize(productsPages.getSize())
                .isLast(productsPages.isLast())
                .build();
    }
    public ProductsResponse findByCategoryId(Integer pageNo, Integer pageSize, String sortBy,Long categoryId){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Category category = categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
        Page<Product> productsPages = productRepository.findByCategory(category,paging);
        List<ProductDTO> productsDTO =  productsPages.stream().map(Util::toDTO).collect(Collectors.toList());

        return ProductsResponse.builder().products(productsDTO)
                .pageNumber(productsPages.getNumber())
                .totalPages(productsPages.getTotalPages())
                .pageSize(productsPages.getSize())
                .isLast(productsPages.isLast())
                .build();
    }

    public ProductsResponse findByCategoryIdNative(Integer pageNo, Integer pageSize, String sortBy,Long categoryId){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Product> productsPages = productRepository.findByCategoryIdWithPagination(categoryId,paging);
        List<ProductDTO> productsDTO =  productsPages.stream().map(Util::toDTO).collect(Collectors.toList());

        return ProductsResponse.builder().products(productsDTO)
                .pageNumber(productsPages.getNumber())
                .totalPages(productsPages.getTotalPages())
                .pageSize(productsPages.getSize())
                .isLast(productsPages.isLast())
                .build();
    }
}
