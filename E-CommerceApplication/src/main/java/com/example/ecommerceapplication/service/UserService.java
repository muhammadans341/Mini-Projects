package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.Exception.ProductNotFoundException;
import com.example.ecommerceapplication.Util;
import com.example.ecommerceapplication.dto.UserDTO;
import com.example.ecommerceapplication.model.Product;
import com.example.ecommerceapplication.model.User;
import com.example.ecommerceapplication.repository.UserRepository;
import com.example.ecommerceapplication.response.ProductsResponse;
import com.example.ecommerceapplication.response.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    UserRepository userRepository;
    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public UserDTO createUser(UserDTO userDTO){
        User user = Util.toUserEntity(userDTO);
        user.setDate(new Date());
        return Util.toUserDTO(userRepository.save(user));
    }

    public UserDTO getUser(Long userId){
        Optional<User> user = userRepository.findById(userId);
        return user.map(Util::toUserDTO).orElseThrow(EntityNotFoundException::new);
    }

    public UserResponse getAllUsers(Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<User> userPages = userRepository.findAll(paging);
        List<UserDTO> users = userPages.stream().map(Util::toUserDTO).collect(Collectors.toList());

        return UserResponse.builder().users(users)
                .pageNumber(userPages.getNumber())
                .totalPages(userPages.getTotalPages())
                .pageSize(userPages.getSize())
                .isLast(userPages.isLast())
                .build();
    }
    public void deleteUserById(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new ProductNotFoundException("User not found for deletion"));
        userRepository.delete(user);
    }

    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ProductNotFoundException("User not found for Update"));
        BeanUtils.copyProperties(Util.toUserEntity(userDTO),user,Util.getNullPropertyNames(userDTO));
        return Util.toUserDTO(userRepository.save(user));
    }
}
