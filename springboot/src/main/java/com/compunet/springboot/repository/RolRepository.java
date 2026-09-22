package com.compunet.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compunet.springboot.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    public Optional<Rol> findByNombre(String nombreRol);
    
}
