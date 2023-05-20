package com.example.movieinfoservice.controller;

import com.example.movieinfoservice.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MoviesController {
    private final WebClient webClient;
    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){
        return webClient.get()
                .uri("https://www.omdbapi.com/?i=" + movieId + "&apikey=f8066528")
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
    }
}
