package com.niten.moviecatalogservice.resources;

import com.niten.moviecatalogservice.models.CatalogItem;
import com.niten.moviecatalogservice.models.UserRating;
import com.niten.moviecatalogservice.services.MovieInfo;
import com.niten.moviecatalogservice.services.UserRatingInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    Logger logger = LoggerFactory.getLogger(MovieCatalogResource.class);

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        logger.info("Searching movie ratings for userId: " + userId);

        // Get all user rated movie IDs
        UserRating userRating = userRatingInfo.getUserRatings(userId);


        // For each movie id call movie info service and get details
        return userRating.getUserRatings().stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());
    }
}
