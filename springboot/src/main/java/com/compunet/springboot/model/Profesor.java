package com.compunet.springboot.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Collate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nombre", nullable = false)
    private String name;

    @Column (name = "apellido", nullable = false)
    private String Apellido;

    @Column (name = "correo_institucional", nullable = false, unique = true, length = 120)
    private String correoInstitucional;

    @Column (name = "departamento")
    private String departamento;

    @Column (name = "especialidad")
    private String especialidad;

    @Column (name = "active")
    private boolean active;

    @JsonIgnoreProperties (value = "profesor")
    @OneToMany (mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curso> cursos = new ArrayList<>();
    
}
