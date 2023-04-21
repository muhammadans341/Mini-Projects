package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.dto.UserDTO;
import com.example.ecommerceapplication.response.UserResponse;
import com.example.ecommerceapplication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    UserService userService;
    UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@Valid  @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.FOUND);
    }
    @GetMapping("/")
    public ResponseEntity<UserResponse> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                    @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(userService.getAllUsers(pageNo,pageSize,sortBy), HttpStatus.CREATED);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long userId){
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId,@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.updateUser(userId,userDTO),HttpStatus.OK);
    }
}
