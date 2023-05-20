package com.example.userratingsservice.controller;

import com.example.userratingsservice.model.Rating;
import com.example.userratingsservice.responses.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingdata")
public class MovieRatingController {

    @GetMapping("/movieId")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId,4);
    }

    @GetMapping("/users/{userid}")
    public UserRating getRatingByUser(@PathVariable("userid") String userId){
         List<Rating> ratingList= Arrays.asList(
                 new Rating("tt1567432",8),
                 new Rating("tt1190634",9)
        );
        return new UserRating(ratingList);

    }
}

