package com.niten.ratingsdataservice.resources;

import com.niten.ratingsdataservice.models.Rating;
import com.niten.ratingsdataservice.models.UserRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.SpringVersion;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
    Logger logger = LoggerFactory.getLogger(RatingResource.class);

    @RequestMapping("/users/{userId}")
    public UserRating getMovies(@PathVariable("userId") String userId) {
        logger.info("Searching movie rating for userId: " + userId);

        final List<Rating> ratings = Arrays.asList(
                new Rating("300", 4),
                new Rating("306", 5)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRatings(ratings);
        return userRating;
    }

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        System.out.println(SpringVersion.getVersion());
        return new Rating(movieId, 4);
    }

}
