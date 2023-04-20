package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.response.ProductsResponse;
import com.example.ecommerceapplication.service.CategoryService;
import com.example.ecommerceapplication.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pms/v1/products")
public class ProductController {
    private static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/productImages";
    ProductService productService;
    CategoryService categoryService;
    ProductController(ProductService productService, CategoryService categoryService){
        this.productService=productService;
        this.categoryService=categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
       return new ResponseEntity<>(productService.addProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId){
        ProductDTO productDTO = productService.getProduct(productId);
        if(productDTO!=null){
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public ResponseEntity<ProductsResponse> getAllProducts(@RequestParam(defaultValue = "0") Integer pageNo,
                                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                                           @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(productService.getAllProducts(pageNo,pageSize,sortBy),HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId,@RequestBody ProductDTO productDTO){
        ProductDTO updatedProduct = productService.updateProduct(productId,productDTO);
        if(updatedProduct!=null){
            return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}
