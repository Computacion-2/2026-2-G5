package com.compunet.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compunet.springboot.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    
}
 
