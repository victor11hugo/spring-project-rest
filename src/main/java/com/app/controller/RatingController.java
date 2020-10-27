package com.app.controller;


import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Rating;
import com.app.exception.RatingNotFoundException;
import com.app.repository.RatingRepository;

@RestController
@RequestMapping("/movies-catalog")
public class RatingController {
    @Autowired
    private RatingRepository ratingRepository;

    @GetMapping("/ratings")
    public List < Rating > getAllRatings() {
        return ratingRepository.findAll();
    }

    @GetMapping("/rating/{id}")
    public ResponseEntity < Rating > getRatingById(@PathVariable(value = "id") Long id)
    throws RatingNotFoundException {
    	Rating rating = ratingRepository.findById(id)
            .orElseThrow(() -> new RatingNotFoundException("Rating not found for this id : " + id));
        return ResponseEntity.ok().body(rating);
    }

    @PostMapping("/rating")
    public Rating createRating(@RequestBody Rating rating) {
        return ratingRepository.save(rating);
    }

    @PutMapping("/rating/{id}")
    public ResponseEntity < Rating > updateRating(@PathVariable(value = "id") Long id,
        @RequestBody Rating ratingDetails) throws RatingNotFoundException {
    	Rating rating = ratingRepository.findById(id)
            .orElseThrow(() -> new RatingNotFoundException("Rating not found for this id : " + id));

    	rating.setId(id);
    	rating.setValue(ratingDetails.getValue());
        final Rating updatedRating = ratingRepository.save(rating);
        return ResponseEntity.ok(updatedRating);
    }

    @DeleteMapping("/rating/{id}")
    public Map < String, Boolean > deleteRating(@PathVariable(value = "id") Long id)
    throws RatingNotFoundException {
    	Rating rating = ratingRepository.findById(id)
            .orElseThrow(() -> new RatingNotFoundException("Rating not found for this id : " + id));

        ratingRepository.delete(rating);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}