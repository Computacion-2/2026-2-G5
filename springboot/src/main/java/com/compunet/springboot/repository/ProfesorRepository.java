package com.compunet.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compunet.springboot.model.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    
}
