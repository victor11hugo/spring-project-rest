package com.app.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	@Query(value = "SELECT m FROM Movie m WHERE m.director.name = ?1")
	List<Movie> getMoviesByDirectorName(String name);
	
	@Query(value = "SELECT m FROM Movie m WHERE m.rating.value >= ?1")
	List<Movie> getMoviesWithRatingAbove(Long value);
	
}