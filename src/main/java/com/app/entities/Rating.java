package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_RATING", schema = "PROJECT")
@SequenceGenerator(name = "RATING_SEQ", sequenceName = "RATING_SEQ", allocationSize = 1)
@Getter
@Setter
@DynamicUpdate
public class Rating {

    @Id
    @GeneratedValue(generator = "RATING_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_RATING")
    private Long id;

    @Column(name = "RT_VALUE", length = 10)
    private Long value;  
    
 }

