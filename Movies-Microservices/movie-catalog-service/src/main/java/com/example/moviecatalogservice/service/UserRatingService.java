package com.example.moviecatalogservice.service;

import com.example.moviecatalogservice.model.Rating;
import com.example.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserRatingService {
    private final RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getDefaultUserRating")
    public UserRating getUserRating(String userId) {
        UserRating userRating = restTemplate.getForObject("http://user-rating-service/ratingdata/users/"+ userId, UserRating.class);
        return userRating;
    }
    public UserRating getDefaultUserRating(String userId) {
        return new UserRating(
                Collections.singletonList(new Rating("tt7233726",10))
        );
    }
}
