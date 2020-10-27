package com.app.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.app.entities.Movie;
import com.app.exception.MovieNotFoundException;
import com.app.repository.MovieRepository;

@Service
public class MovieService {
	
	public static final String EXCEPTION_MSG = "Movie not found for this id : ";
	
	@Autowired
    private MovieRepository movieRepository;
	    
	    public List <Movie> getAllMovies() {
	        return movieRepository.findAll();
	    }
	    
	    public List <Movie> getMoviesByDirectorName(String directorName) {
	        return movieRepository.getMoviesByDirectorName(directorName);
	    }
	    
	    public List <Movie> getMoviesByRating(Long ratingValue) {
	        return movieRepository.getMoviesWithRatingAbove(ratingValue);
	    }

	    public Movie getMovieById(Long id) throws MovieNotFoundException {
	        return movieRepository.findById(id)
		            .orElseThrow(() -> new MovieNotFoundException(EXCEPTION_MSG + id));
	    }

	    public Movie createMovie(Movie movie) {
	        return movieRepository.save(movie);
	    }

	    public Movie updateMovie(Long id, Movie movieDetails) throws MovieNotFoundException {
	        Movie movie = movieRepository.findById(id)
	            .orElseThrow(() -> new MovieNotFoundException(EXCEPTION_MSG + id));

	        movie.setId(id);
	        movie.setName(movieDetails.getName());
	        movie.setGenre(movieDetails.getGenre());
	        movie.setReleaseDate(movieDetails.getReleaseDate());
	        return movieRepository.save(movie);
	    }

	    public Map < String, Boolean > delete(Long id)
	    throws MovieNotFoundException {
	        Movie movie = movieRepository.findById(id)
	            .orElseThrow(() -> new MovieNotFoundException(EXCEPTION_MSG + id));

	        movieRepository.delete(movie);
	        Map < String, Boolean > response = new HashMap < > ();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	}

