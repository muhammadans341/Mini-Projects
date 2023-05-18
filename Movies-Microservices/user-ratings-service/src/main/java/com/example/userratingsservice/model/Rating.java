package com.example.userratingsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Rating {
    String movieId;
    int rating;
}
