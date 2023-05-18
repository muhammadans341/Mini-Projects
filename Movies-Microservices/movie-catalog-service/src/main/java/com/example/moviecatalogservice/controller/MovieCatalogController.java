package com.example.moviecatalogservice.controller;
import com.example.moviecatalogservice.model.CatalogItem;
import com.example.moviecatalogservice.model.Movie;
import com.example.moviecatalogservice.model.Rating;
import com.example.moviecatalogservice.model.UserRating;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class MovieCatalogController {

    private final RestTemplate restTemplate;

    @GetMapping("{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String UserId){

        UserRating userRating = restTemplate.getForObject("http://localhost:8092/ratingdata/users/"+UserId, UserRating.class);

        return userRating.getRatingList().stream().map(rating -> {
            Movie movie =restTemplate.getForObject("http://localhost:8091/movies/1", Movie.class);
            return new CatalogItem(movie.getName(),"desc",rating.getRating());
        }).collect(Collectors.toList());
    }
}
