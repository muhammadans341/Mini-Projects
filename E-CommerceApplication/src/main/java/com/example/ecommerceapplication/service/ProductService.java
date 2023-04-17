package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.Exception.ProductNotFoundException;
import com.example.ecommerceapplication.Util;
import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.model.Product;
import com.example.ecommerceapplication.repository.CetagoryRepository;
import com.example.ecommerceapplication.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

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
       if(product.isPresent()){
           return Util.toDTO(product.get());
       }
       return null;
    }

    public void deleteProductById(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product not found for deletion"));
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

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(Util::toDTO).collect(Collectors.toList());
    }
}
