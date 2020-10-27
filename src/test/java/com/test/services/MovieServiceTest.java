package com.test.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.entities.Movie;
import com.app.exception.MovieNotFoundException;
import com.app.repository.MovieRepository;
import com.app.services.MovieService;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {
	
	@InjectMocks
    private MovieService movieService;

	@Mock
    private MovieRepository movieRepository;
	
	Movie m;
	
    @Before
    public void init() {
    	m = new Movie();
    	m.setId(1L);
    	when(movieRepository.findById(any(Long.class))).thenReturn(Optional.of(m));
    	when(movieRepository.save(any(Movie.class))).thenReturn(m);
    }
    
    @Test
    public void sucess_when_get_movies_by_director_name() {
		assertNotNull(movieService.getMoviesByDirectorName("any"));
	}
    
    @Test
    public void sucess_when_get_movies_by_rating_value() {
    	assertNotNull(movieService.getMoviesByRating(1L));
	}
    
    @Test
    public void sucess_when_update_movie() throws MovieNotFoundException {
    	assertNotNull(movieService.updateMovie(1L, new Movie()));
	}
    
    @Test (expected = MovieNotFoundException.class)
    public void error_when_update_movie() throws MovieNotFoundException {
    	when(movieRepository.findById(any(Long.class))).thenReturn(Optional.empty());
    	assertNotNull(movieService.updateMovie(1L, new Movie()));
	}
}
