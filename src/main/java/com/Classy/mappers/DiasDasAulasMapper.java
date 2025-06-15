package com.Classy.mappers;

import com.Classy.DTO.DiasDasAulasDTO;
import com.Classy.entitys.DiasDaAula;

public class DiasDasAulasMapper {

    public static DiasDasAulasDTO toDTO(DiasDaAula entity){
        DiasDasAulasDTO dto = new DiasDasAulasDTO();
        dto.setId(entity.getId());
        dto.setDia_semana(entity.getDiaDaSemana());
        dto.setHorario(entity.getHorario());
        return dto;
    }

    public static DiasDaAula toEntity(DiasDasAulasDTO dto){
        DiasDaAula entity = new DiasDaAula();
        entity.setDiaDaSemana(dto.getDia_semana());
        entity.setHorario(dto.getHorario());
        return entity;
    }
}
