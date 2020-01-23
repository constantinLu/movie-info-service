package com.sonuswaves.movieinfoservice.resources;


import com.sonuswaves.movieinfoservice.models.Movie;
import com.sonuswaves.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    private static final String URL = "https://api.themoviedb.org/3/movie/";

    //get the value from the app.prop file.
    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public MovieResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
            MovieSummary movieSummary = restTemplate.getForObject(URL + movieId + "?api_key=" + apiKey, MovieSummary.class);
        return new Movie(movieSummary.getId(), movieSummary.getTitle(), movieSummary.getOverview());
    }
}
