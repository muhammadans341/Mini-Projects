package com.example.ecommerceapplication.utility;

import com.example.ecommerceapplication.dto.CategoryDTO;
import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.dto.UserDTO;
import com.example.ecommerceapplication.enums.Gender;
import com.example.ecommerceapplication.model.Category;
import com.example.ecommerceapplication.model.Product;
import com.example.ecommerceapplication.model.Role;
import com.example.ecommerceapplication.model.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
    static ModelMapper modelMapper = new ModelMapper();

    public static Product toEntity(ProductDTO productDTO){
        return modelMapper.map(productDTO,Product.class);
    }

    public static ProductDTO toDTO(Product product){
        return modelMapper.map(product,ProductDTO.class);
    }

    public static User toUserEntity(UserDTO userDTO){
        TypeMap<UserDTO, User> typeMap = modelMapper.getTypeMap(UserDTO.class, User.class);
        if(typeMap==null) {
            typeMap = modelMapper.createTypeMap(UserDTO.class, User.class);
            Converter<Gender, String> genderStringConverter = mappingContext -> mappingContext.getSource().getGenderValue();
            Converter<Set<String>,Set<Role>> rolesConvertor = mappingContext -> mappingContext.getSource().stream().map(roleName ->{
                Role role = new Role();
                role.setRoleName(roleName);
                return role;
            }).collect(Collectors.toSet());
            typeMap.addMappings(mapper -> mapper.using(genderStringConverter).map(UserDTO::getGender, User::setGender));
            typeMap.addMappings(mapper -> mapper.using(rolesConvertor).map(UserDTO::getRoles,User::setRoles));
        }
        return modelMapper.map(userDTO,User.class);
    }

    public static UserDTO toUserDTO(User user){
        TypeMap<User, UserDTO> typeMap = modelMapper.getTypeMap(User.class, UserDTO.class);
        if(typeMap==null){
            typeMap = modelMapper.createTypeMap(User.class, UserDTO.class);
            Converter<String,Gender> stringGenderConverter = mappingContext-> Stream.of(Gender.values())
                    .filter(gender -> gender.getGenderValue().equals(mappingContext.getSource()))
                    .findFirst()
                    .orElse(Gender.OTHER);
            Converter<Set<Role>,Set<String>> roleConvertor= mappingContext -> mappingContext.getSource().stream().map(Role::getRoleName).collect(Collectors.toSet());
            typeMap.addMappings(mapper -> mapper.using(stringGenderConverter).map(User::getGender, UserDTO::setGender));
            typeMap.addMappings(mapper -> mapper.using(roleConvertor).map(User::getRoles,UserDTO::setRoles));
        }
        return modelMapper.map(user,UserDTO.class);
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
