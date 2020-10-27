package com.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long>{

}