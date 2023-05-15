package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.utility.Util;
import com.example.ecommerceapplication.dto.CategoryDTO;
import com.example.ecommerceapplication.model.Category;
import com.example.ecommerceapplication.repository.CetagoryRepository;
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
public class CategoryService {
    CetagoryRepository categoryRepository;
    public CategoryService(CetagoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    public CategoryDTO addCategory(CategoryDTO categoryDTO){
        Category category =Util.toCatEntity(categoryDTO);
        Category savedCategory =categoryRepository.save(category);
        return Util.toCatDTO(savedCategory);
    }

    public CategoryDTO updateCategory(Long categoryId, CategoryDTO updatedCategoryDTO){
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new EntityNotFoundException("Product not found with id:"+categoryId));
        BeanUtils.copyProperties(updatedCategoryDTO, category, Util.getNullPropertyNames(updatedCategoryDTO));
        Category updatedCategory = categoryRepository.save(category);
        return Util.toCatDTO(updatedCategory);
    }

    public void deleteCategory(Long categoryId){
        categoryRepository.deleteById(categoryId);
    }

    public CategoryDTO getCategoryById(Long categoryId) {
        Optional<Category> category=categoryRepository.findById(categoryId);
        return category.map(Util::toCatDTO).orElse(null);
    }

    public List<CategoryDTO> getAllCategories(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Category> categories = categoryRepository.findAll(paging);
        return categories.stream().map(Util::toCatDTO).collect(Collectors.toList());
    }
}
