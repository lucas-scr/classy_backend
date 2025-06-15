package com.Classy.mappers;

import com.Classy.DTO.ContatoDTO;
import com.Classy.entitys.Contato;

public class ContatoMapper {

    public static ContatoDTO toDTO(Contato entity){
        ContatoDTO dto = new ContatoDTO();
        dto.setId(entity.getId());
        dto.setPrincipal(entity.getPrincipal());
        dto.setResponsavel(entity.getResponsavel());
        dto.setTelefone(entity.getTelefone());
        return dto;

    }

    public static Contato toEntity(ContatoDTO dto){
        Contato entity = new Contato();
        entity.setPrincipal(dto.getPrincipal());
        entity.setTelefone(dto.getTelefone());
        entity.setResponsavel(dto.getResponsavel());
        entity.setContrato(dto.getContrato());
        return entity;
    }
}
