package com.example.ecommerceapplication;

import com.example.ecommerceapplication.dto.CategoryDTO;
import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.model.Category;
import com.example.ecommerceapplication.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class Util {
    static ModelMapper modelMapper = new ModelMapper();
    static ModelMapper modelMapperCategory = new ModelMapper();

    public static Product toEntity(ProductDTO productDTO){
        Product product = modelMapper.map(productDTO,Product.class);
        return product;
    }

    public static ProductDTO toDTO(Product product){
        ProductDTO productDTO = modelMapper.map(product,ProductDTO.class);
        return productDTO;
    }

    public static Category toCatEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }
    public static CategoryDTO toCatDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
