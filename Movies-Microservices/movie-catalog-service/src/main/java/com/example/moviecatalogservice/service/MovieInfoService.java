package com.example.moviecatalogservice.service;

import com.example.moviecatalogservice.model.CatalogItem;
import com.example.moviecatalogservice.model.Movie;
import com.example.moviecatalogservice.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MovieInfoService {
    private final RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getDefaultCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getTitle(), "desc", rating.getRating());
    }
    public CatalogItem getDefaultCatalogItem(Rating rating) {
        return new CatalogItem("No Movie","desc", rating.getRating());
    }
}
