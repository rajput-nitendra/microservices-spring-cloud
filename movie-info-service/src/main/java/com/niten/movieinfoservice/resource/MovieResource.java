package com.niten.movieinfoservice.resource;

import com.niten.movieinfoservice.models.Movie;
import com.niten.movieinfoservice.models.MovieSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {
    Logger logger = LoggerFactory.getLogger(MovieResource.class);

    @Value("${moviedb.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        logger.info("Searching movie info for movieId: " + movieId);

//        https://api.themoviedb.org/3/movie/551?api_key=b37dba5cd1f22541f28b1a4612001564

        String url = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey;

        MovieSummary movieSummary = restTemplate.getForObject(url, MovieSummary.class);

        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }
}
