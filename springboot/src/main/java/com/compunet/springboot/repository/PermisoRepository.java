package com.compunet.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compunet.springboot.model.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {
    
}
