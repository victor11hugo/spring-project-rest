package com.app.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Movie;
import com.app.exception.MovieNotFoundException;
import com.app.services.MovieService;

@RestController
@RequestMapping("/movies-catalog")
public class MovieController {
	
    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public List < Movie > getAllMovies() {
        return movieService.getAllMovies();
    }
    
    @GetMapping("/movies/byDirectorsName")
    public List < Movie > getMoviesByDirectorName(@RequestParam(value="directorName") String directorName) {
        return movieService.getMoviesByDirectorName(directorName);
    }
    
    @GetMapping("/movies/byRatingValue")
    public List < Movie > getMoviesByRating(@RequestParam(value="ratingValue") Long ratingValue) {
        return movieService.getMoviesByRating(ratingValue);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity < Movie > getMovieById(@PathVariable(value = "id") Long id) throws MovieNotFoundException {
        return ResponseEntity.ok().body(movieService.getMovieById(id));
    }

    @PostMapping("/movie")
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity < Movie > updateMovie(@PathVariable(value = "id") Long id,
        @RequestBody Movie movieDetails) throws MovieNotFoundException {        
        return ResponseEntity.ok(movieService.updateMovie(id, movieDetails));
    }

    @DeleteMapping("/movie/{id}")
    public Map < String, Boolean > deleteMovie(@PathVariable(value = "id") Long id)
    throws MovieNotFoundException {
        return movieService.delete(id);
    }
}