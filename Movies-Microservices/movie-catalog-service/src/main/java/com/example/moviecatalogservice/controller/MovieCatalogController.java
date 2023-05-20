package com.example.moviecatalogservice.controller;

import com.example.moviecatalogservice.model.CatalogItem;
import com.example.moviecatalogservice.model.Movie;
import com.example.moviecatalogservice.model.Rating;
import com.example.moviecatalogservice.model.UserRating;
import com.example.moviecatalogservice.service.MovieInfoService;
import com.example.moviecatalogservice.service.UserRatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class MovieCatalogController {

    private final UserRatingService userRatingService;
    private final MovieInfoService movieInfoService;
    @GetMapping("{userId}")

    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        UserRating userRating = userRatingService.getUserRating(userId);
        return userRating.getRatingList().stream().map(
                movieInfoService::getCatalogItem
        ).collect(Collectors.toList());
    }

}
