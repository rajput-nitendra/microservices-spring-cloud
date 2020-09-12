package com.niten.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.niten.moviecatalogservice.models.CatalogItem;
import com.niten.moviecatalogservice.models.Movie;
import com.niten.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Service
public class MovieInfo {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;


    /*@HystrixCommand(fallbackMethod = "getFallbackCatalogItem", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "5000")
    })*/
    public CatalogItem getCatalogItem(Rating rating) {

        Movie movie = restTemplate
                .getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

                    /*
                    Movie movie = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8072/movies/" + rating.getMovieId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();
                    */

        // Put them altogether
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie name not found", "", rating.getRating());
    }
}
