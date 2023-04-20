package com.example.ecommerceapplication.response;

import com.example.ecommerceapplication.dto.ProductDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProductsResponse {
    private List<ProductDTO> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private boolean isLast;
}
