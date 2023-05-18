package com.example.userratingsservice.responses;

import com.example.userratingsservice.model.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRating {
    List<Rating> ratingList;
}
