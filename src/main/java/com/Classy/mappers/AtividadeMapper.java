package com.Classy.mappers;

import com.Classy.DTO.AtividadeDTO;
import com.Classy.entitys.Atividade;
import com.Classy.entitys.Materia;

public class AtividadeMapper {

    public static AtividadeDTO toDTO(Atividade atividade){
        if (atividade == null) return null;
        AtividadeDTO atividadeDTO = new AtividadeDTO();
        atividadeDTO.setUrl(atividade.getUrl());
        atividadeDTO.setCodigo(atividade.getCodigo());
        atividadeDTO.setDescricao(atividade.getDescricao());
        atividadeDTO.setId(atividade.getId());
        atividadeDTO.setArquivo(atividade.getArquivo());
        atividadeDTO.setDataCriacao(atividade.getDataCriacao());
        atividadeDTO.setMateria(atividade.getMateria());
//        if(atividade.getMateria() != null){
//            atividadeDTO.setMateriaId(atividade.getMateria().getId());
//        }else {
//            atividadeDTO.setMateriaId(null);
//        }
        return atividadeDTO;
    }

    public static Atividade toEntity(AtividadeDTO atividadeDTO){
        if (atividadeDTO == null) return null;
        Atividade atividade = new Atividade();
        atividade.setCodigo(atividadeDTO.getCodigo());
        atividade.setDescricao(atividadeDTO.getDescricao());
        atividade.setArquivo(atividadeDTO.getArquivo());
        atividade.setUrl(atividadeDTO.getUrl());
        atividade.setMateria(atividadeDTO.getMateria());
//        if(atividadeDTO.getMateriaId() != null){
//            Materia materia = new Materia();
//            materia.setId(atividadeDTO.getMateriaId());
//            atividade.setMateria(materia);
//        }else {
//            atividade.setMateria(null);
//        }
        return  atividade;
    }

    public static void updateEntityFromDTO(AtividadeDTO atividadeDTO, Atividade atividade){
        if (atividadeDTO == null || atividade == null) return;
        atividade.setDescricao(atividadeDTO.getDescricao());
        atividade.setArquivo(atividadeDTO.getArquivo());
        atividade.setUrl(atividadeDTO.getUrl());
        atividade.setMateria(atividadeDTO.getMateria());
//        if(atividadeDTO.getMateriaId() != null){
//            Materia materia = new Materia();
//            materia.setId(atividadeDTO.getMateriaId());
//            atividade.setMateria(materia);
//        }else {
//            atividade.setMateria(null);
//        }
    }

}
