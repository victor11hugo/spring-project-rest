package com.app.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_DIRECTOR", schema = "PROJECT")
@SequenceGenerator(name = "DIRECTOR_SEQ", sequenceName = "DIRECTOR_SEQ", allocationSize = 1)
@Getter
@Setter
@DynamicUpdate
public class Director {

    @Id
    @GeneratedValue(generator = "DIRECTOR_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_DIRECTOR")
    private Long id;

    @Column(name = "DR_NAME", length = 100)
    private String name;
    
    @JsonManagedReference
    @OneToMany(mappedBy="director", cascade = CascadeType.ALL)
    private Set<Movie> movies = new HashSet<>();
    
    public void addMovie(Movie m) {
    	this.movies.add(m);
		m.setDirector(this);
	}
    
    public void removeMovie(Movie m) {
    	this.movies.remove(m);
	} 
 
}

