package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.dto.CategoryDTO;
import com.example.ecommerceapplication.model.Category;
import com.example.ecommerceapplication.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/pms/v1/categories")
public class CategoryController {
    CategoryService categoryService;
    CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.addCategory(categoryDTO), HttpStatus.OK);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId,
                                                      @Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO c = categoryService.updateCategory(categoryId,categoryDTO);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> createCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/fetch/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long categoryId){
        CategoryDTO result= categoryService.getCategoryById(categoryId);
        if(result!=null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(@RequestParam(defaultValue = "0") Integer pageNo,
                                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                                             @RequestParam(defaultValue = "id") String sortBy){
        List<CategoryDTO> result= categoryService.getAllCategories(pageNo,pageSize,sortBy);
        if(result!=null && result.size()!=0){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

}
