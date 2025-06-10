package com.Classy.mappers;

import com.Classy.DTO.MateriaDTO;
import com.Classy.entitys.Materia;
import com.google.api.client.util.DateTime;

import java.time.LocalDateTime;
import java.util.Date;

public class MateriaMapper {

     public static MateriaDTO toDTO(Materia materia){
         MateriaDTO materiaDTO = new MateriaDTO();
         materiaDTO.setId(materia.getId());
         materiaDTO.setNome(materia.getNome());
         materiaDTO.setDataCriacao(materia.getDataCriacao());

         return materiaDTO;
     }

     public static Materia toEntity(MateriaDTO materiaDTO){
         Materia materia = new Materia();
         materia.setNome(materiaDTO.getNome());
         return materia;
     }
}
