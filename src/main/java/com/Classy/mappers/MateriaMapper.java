package com.Classy.mappers;

import com.Classy.DTO.MateriaDTO;
import com.Classy.entitys.Materia;

public class MateriaMapper {

     public static MateriaDTO toDTO(Materia materia){
         MateriaDTO materiaDTO = new MateriaDTO();
         materiaDTO.setId(materia.getId());
         materiaDTO.setNome(materia.getNome());
         materiaDTO.setDataCriacao(materia.getDataCriacao());

         return materiaDTO;
     }

     public static Materia toEntity(MateriaDTO materiaDTO){
         if(materiaDTO == null || materiaDTO.getId() == null){
             return null;
         }
         Materia materia = new Materia();
         materia.setNome(materiaDTO.getNome());

         return materia;
     }

     public static Materia toEntityReferencia(MateriaDTO materiaDTO){
         if (materiaDTO == null || materiaDTO.getId() == null) {
             return null;
         }
         Materia materia = new Materia();
         materia.setId(materiaDTO.getId());
         return materia;
     }
}
