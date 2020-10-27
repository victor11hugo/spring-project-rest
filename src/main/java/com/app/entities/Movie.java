package com.app.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_MOVIE", schema = "PROJECT")
@SequenceGenerator(name = "MOVIE_SEQ", sequenceName = "MOVIE_SEQ", allocationSize = 1)
@Getter
@Setter
@DynamicUpdate
public class Movie {

    @Id
    @GeneratedValue(generator = "MOVIE_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_MOVIE")
    private Long id;

    @Column(name = "MV_NAME", length = 100)
    private String name;

    @Column(name = "MV_GENRE", length = 20)
    private String genre;

	@Column(name = "MV_DATE")
	private String releaseDate;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_RATING")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Rating rating;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn (name="ID_DIRECTOR", nullable=false)
	private Director director;
	
	public void addDirector(Director d) {
		this.setDirector(d);
		d.getMovies().add(this);
	}
		  
}

