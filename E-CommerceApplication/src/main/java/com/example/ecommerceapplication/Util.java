package com.example.ecommerceapplication;

import com.example.ecommerceapplication.dto.CategoryDTO;
import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.dto.UserDTO;
import com.example.ecommerceapplication.enums.Gender;
import com.example.ecommerceapplication.model.Category;
import com.example.ecommerceapplication.model.Product;
import com.example.ecommerceapplication.model.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Util {
    static ModelMapper modelMapper = new ModelMapper();
    static{
        Converter<Gender,String> converter1 = mappingContext -> mappingContext.getSource().getGenderValue();
        modelMapper.addConverter(converter1);
    }


    public static Product toEntity(ProductDTO productDTO){
        Product product = modelMapper.map(productDTO,Product.class);
        return product;
    }

    public static ProductDTO toDTO(Product product){
        ProductDTO productDTO = modelMapper.map(product,ProductDTO.class);
        return productDTO;
    }

    public static User toUserEntity(UserDTO userDTO){
        User user = modelMapper.map(userDTO,User.class);
        return user;
    }

    public static UserDTO toUserDTO(User user){
        TypeMap<User, UserDTO> typeMap = modelMapper.getTypeMap(User.class, UserDTO.class);
        if(typeMap==null){
            typeMap = modelMapper.createTypeMap(User.class, UserDTO.class);
            Converter<String,Gender> converter2 = mappingContext-> Stream.of(Gender.values())
                    .filter(gender -> gender.getGenderValue().equals(mappingContext.getSource()))
                    .findFirst()
                    .orElse(Gender.OTHER);
            typeMap.addMappings(mapper -> mapper.using(converter2).map(User::getGender, UserDTO::setGender));
        }
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
        return userDTO;
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
