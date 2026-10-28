package com.compunet.springboot.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.compunet.springboot.dtos.Cursos.CursoRequestDTO;
import com.compunet.springboot.dtos.Cursos.CursoResponseDTO;
import com.compunet.springboot.model.Curso;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mapping(target = "profesorId", source = "profesor.id")
    @Mapping(target = "profesorNombre", source = "profesor.usuario.nombre")
    @Mapping(target = "profesorDepartamento", source = "profesor.departamento")
    CursoResponseDTO toCursoResponseDTO(Curso curso);

    List<CursoResponseDTO> toListCursoResponseDTO(List<Curso> cursos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "profesor", ignore = true)
    @Mapping(target = "matriculas", ignore = true)
    Curso toCurso(CursoRequestDTO requestDTO);
}
