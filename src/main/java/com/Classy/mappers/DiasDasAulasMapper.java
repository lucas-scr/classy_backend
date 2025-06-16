package com.Classy.mappers;

import com.Classy.DTO.DiasDasAulasDTO;
import com.Classy.entitys.DiasDaAula;

public class DiasDasAulasMapper {

    public static DiasDasAulasDTO toDTO(DiasDaAula entity){
        DiasDasAulasDTO dto = new DiasDasAulasDTO();
        dto.setId(entity.getId());
        dto.setDiaSemana(entity.getDiaDaSemana());
        dto.setHorario(entity.getHorario());
        return dto;
    }

    public static DiasDaAula toEntity(DiasDasAulasDTO dto){
        DiasDaAula diaEntity = new DiasDaAula();
        if(dto.getId() != null){
            System.out.println(dto.getId());
            diaEntity.setId(dto.getId());
        }
        diaEntity.setDiaDaSemana(dto.getDiaSemana());
        diaEntity.setHorario(dto.getHorario());
        return diaEntity;
    }
}
