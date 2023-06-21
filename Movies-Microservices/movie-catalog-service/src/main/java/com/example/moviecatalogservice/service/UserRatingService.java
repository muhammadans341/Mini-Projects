package com.example.moviecatalogservice.service;

import com.example.moviecatalogservice.model.Rating;
import com.example.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserRatingService {

    @Value("${service.url.user-rating}")
    private String userRatingUrl;

    private final RestTemplate restTemplate;
    @HystrixCommand(
            fallbackMethod = "getDefaultUserRating",
            threadPoolKey = "userRatingThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "20"),
                    @HystrixProperty(name="maxQueueSize",value = "10")
            }
    )
    public UserRating getUserRating(String userId) {
        UserRating userRating = restTemplate.getForObject(userRatingUrl+"/ratingdata/users/"+ userId, UserRating.class);
        return userRating;
    }
    public UserRating getDefaultUserRating(String userId) {
        return new UserRating(
                Collections.singletonList(new Rating("tt7233726",10))
        );
    }
}
