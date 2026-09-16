package com.compunet.springboot.model;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table (name = "curso")
public class Curso {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nombre")
    private String nombre;

    @Column (name = "creditos")
    private Integer creditos;
    
    @Column (name = "departamento")
    private String departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id", nullable = false)
    @JsonIgnoreProperties (value = "cursos")
    private Profesor profesor;

    @OneToMany (mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore 
    @JsonIgnoreProperties (value = "curso")
    private List<EstudianteCurso> estudianteCursos = new ArrayList<>();
    
}
