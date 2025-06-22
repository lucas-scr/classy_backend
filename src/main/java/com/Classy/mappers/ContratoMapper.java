package com.Classy.mappers;

import com.Classy.DTO.ContratoDTO;
import com.Classy.DTO.DiasDasAulasDTO;
import com.Classy.entitys.Aluno;
import com.Classy.entitys.Contato;
import com.Classy.entitys.Contrato;
import com.Classy.entitys.DiasDaAula;

import java.util.*;
import java.util.stream.Collectors;


public class ContratoMapper {

    public static ContratoDTO toDTO(Contrato contrato){
        ContratoDTO dto = new ContratoDTO();
        dto.setId(contrato.getId());
        dto.setNomeResponsavel(contrato.getNomeResponsavel());
        dto.setDocumentoResponsavel(contrato.getDocumentoResponsavel());
        dto.setTelefone(contrato.getTelefone());
        dto.setDiasAlternados(contrato.isDiasAlternados());
        dto.setAluno(AlunoMapper.toDTO(contrato.getAluno()));
        dto.setDiaPagamento(contrato.getDiaPagamento());
        dto.setDataInicio(contrato.getDataInicio());
        dto.setValorPagamento(contrato.getValorPagamento());
        dto.setRessarcimentoEmFeriados(contrato.isRessarcimentoEmFeriados());
        dto.setDiasDasAulas(converterDiasDasAulasEntityParaDTO(contrato));
        dto.setAutorizaUsoDeImagem(contrato.getAutorizaUsoDeImagem());
        dto.setDataCriacao(contrato.getDataCriacao());
        dto.setSituacao(contrato.getSituacao());
        dto.setHorarioDiasAlternados(contrato.getHorarioDiasAlternados());
        return dto;
    }

    public static Contrato toEntity(ContratoDTO contratoDto) {
        Contrato contratoEntity = new Contrato();
        Aluno aluno = AlunoMapper.toEntity(contratoDto.getAluno());
        aluno.setContrato(contratoEntity);
        contratoEntity.setAluno(aluno);
        contratoEntity.setNomeResponsavel(contratoDto.getNomeResponsavel());
        contratoEntity.setDocumentoResponsavel(contratoDto.getDocumentoResponsavel());
        contratoEntity.setListaDeAulas(converterDiasDaAulaParaEntity(contratoDto, contratoEntity));
        contratoEntity.setTelefone(contratoDto.getTelefone());
        contratoEntity.setDiasAlternados(contratoDto.isDiasAlternados());
        contratoEntity.setDiaPagamento(contratoDto.getDiaPagamento());
        contratoEntity.setDataInicio(contratoDto.getDataInicio());
        contratoEntity.setValorPagamento(contratoDto.getValorPagamento());
        contratoEntity.setRessarcimentoEmFeriados(contratoDto.isRessarcimentoEmFeriados());
        contratoEntity.setAutorizaUsoDeImagem(contratoDto.getAutorizaUsoDeImagem());
        contratoEntity.setSituacao(contratoDto.getSituacao());
        if(contratoDto.getHorarioDiasAlternados() != null && contratoDto.isDiasAlternados()){
            contratoEntity.setHorarioDiasAlternados(contratoDto.getHorarioDiasAlternados());
        }else {
            contratoEntity.setHorarioDiasAlternados(null);
        }
        return contratoEntity;
    }

    public static Contrato toUpdateEntity(Contrato entity,ContratoDTO dto) {
        entity.setNomeResponsavel(dto.getNomeResponsavel());
        entity.setDocumentoResponsavel(dto.getDocumentoResponsavel());
        entity.setDiasAlternados(dto.isDiasAlternados());
        entity.setDiaPagamento(dto.getDiaPagamento());
        entity.setDataInicio(dto.getDataInicio());
        entity.setValorPagamento(dto.getValorPagamento());
        entity.setRessarcimentoEmFeriados(dto.isRessarcimentoEmFeriados());
        entity.setAutorizaUsoDeImagem(dto.getAutorizaUsoDeImagem());
        entity.setTelefone(dto.getTelefone());
        atualizarListaDeAulas(dto, entity);
        entity.setAluno(AlunoMapper.toEntity(dto.getAluno()));
        entity.getAluno().setContrato(entity);
        if(dto.getHorarioDiasAlternados() != null && dto.isDiasAlternados()){
            entity.setHorarioDiasAlternados(dto.getHorarioDiasAlternados());
        }else {
            entity.setHorarioDiasAlternados(null);
        }
        return entity;
    }


    private static List<DiasDaAula> converterDiasDaAulaParaEntity(ContratoDTO dto, Contrato entity){

        return dto.getDiasDasAulas().stream()
                .map(diaDTO -> {
                    DiasDaAula diaEntity = DiasDasAulasMapper.toEntity(diaDTO);
                    diaEntity.setContrato(entity);
                    return diaEntity;
                })
                .collect(Collectors.toList());
    }


    private static List<DiasDasAulasDTO> converterDiasDasAulasEntityParaDTO(Contrato entity){
        return entity.getListaDeAulas().stream()
                .map(DiasDasAulasMapper::toDTO).toList();
    }

    private static void atualizarListaDeAulas(ContratoDTO dto, Contrato entity){
        if(dto.getDiasDasAulas() == null){
            return;
        }
        Map<Long, DiasDaAula> aulasExistentes = entity.getListaDeAulas().stream()
                .filter(aula -> aula.getId() != null)
                .collect(Collectors.toMap(DiasDaAula::getId, aula -> aula));

        List<DiasDaAula> novosDiasDeAulas = new ArrayList<>();

        for(DiasDasAulasDTO dtoAula : dto.getDiasDasAulas()){
            DiasDaAula aula;
            if(dtoAula.getId() != null && aulasExistentes.containsKey(dtoAula.getId())){
                aula = aulasExistentes.get(dtoAula.getId());
                aula.setDiaDaSemana(dtoAula.getDiaSemana());
                aula.setHorario(dtoAula.getHorario());
                aulasExistentes.remove(dtoAula.getId());
            }else{
                aula = DiasDasAulasMapper.toEntity(dtoAula);
                aula.setContrato(entity);
            }

            novosDiasDeAulas.add(aula);
        }
        entity.getListaDeAulas().removeIf(aula -> aulasExistentes.containsKey(aula.getId()));
        entity.getListaDeAulas().clear();
        entity.getListaDeAulas().addAll(novosDiasDeAulas);
    }


}
