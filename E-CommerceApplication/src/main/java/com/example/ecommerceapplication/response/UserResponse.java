package com.example.ecommerceapplication.response;

import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserResponse {
    private List<UserDTO> users;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private boolean isLast;
}
