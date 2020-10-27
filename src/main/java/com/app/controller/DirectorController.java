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

import com.app.entities.Director;
import com.app.exception.DirectorNotFoundException;
import com.app.repository.DirectorRepository;

@RestController
@RequestMapping("/movies-catalog")
public class DirectorController {
    @Autowired
    private DirectorRepository directorRepository;

    @GetMapping("/directors")
    public List < Director > getAllDirectors() {
        return directorRepository.findAll();
    }

    @GetMapping("/director/{id}")
    public ResponseEntity < Director > getDirectorById(@PathVariable(value = "id") Long id)
    throws DirectorNotFoundException {
    	Director director = directorRepository.findById(id)
            .orElseThrow(() -> new DirectorNotFoundException("Director not found for this id : " + id));
        return ResponseEntity.ok().body(director);
    }

    @PostMapping("/director")
    public Director createDirector(@RequestBody Director director) {
        return directorRepository.save(director);
    }

    @PutMapping("/director/{id}")
    public ResponseEntity < Director > updateDirector(@PathVariable(value = "id") Long id,
        @RequestBody Director directorDetails) throws DirectorNotFoundException {
    	Director director = directorRepository.findById(id)
            .orElseThrow(() -> new DirectorNotFoundException("Director not found for this id : " + id));

        director.setId(id);
        director.setName(directorDetails.getName());
        final Director updatedDirector = directorRepository.save(director);
        return ResponseEntity.ok(updatedDirector);
    }

    @DeleteMapping("/director/{id}")
    public Map < String, Boolean > deleteDirector(@PathVariable(value = "id") Long id)
    throws DirectorNotFoundException {
    	Director director = directorRepository.findById(id)
            .orElseThrow(() -> new DirectorNotFoundException("Director not found for this id : " + id));

        directorRepository.delete(director);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}