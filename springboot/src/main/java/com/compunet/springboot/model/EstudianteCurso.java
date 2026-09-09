package com.compunet.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Table (name = "estudiante_curso")
public class EstudianteCurso {

    @EmbeddedId 
    private EstudianteCursoId id = new EstudianteCursoId();
    // @Id 
    // @GeneratedValue (strategy = GenerationType.IDENTITY)
    // private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId ("estudianteId")
    @JoinColumn (name = "estudiante_id", nullable = false)
    @JsonIgnoreProperties (value = "estudianteCursos")
    private Estudiante estudiante;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "curso_id", nullable = false)
    @MapsId ("cursoId")
    @JsonIgnoreProperties (value = "estudianteCursos")
    private Curso curso;

    public EstudianteCurso(Estudiante estudiante, Curso curso){
        this.estudiante = estudiante;
        this.curso = curso;
        this.id = new EstudianteCursoId(estudiante.getId(), curso.getId());
    }

}
