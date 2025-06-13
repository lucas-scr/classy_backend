package com.Classy.mappers;

import com.Classy.DTO.ContratoDTO;
import com.Classy.entitys.Contato;
import com.Classy.entitys.Contrato;

public class ContratoMapper {

    public static ContratoDTO toDTO(Contrato contrato){
        ContratoDTO dto = new ContratoDTO();
        dto.setId(contrato.getId());
        dto.setNomeResponsavel(contrato.getNomeResponsavel());
        dto.setDocumentoResponsavel(contrato.getDocumentoResponsavel());
        String telefonePrincipal = contrato.getListaContatos().stream()
                .filter(Contato::getPrincipal)
                .map(Contato::getTelefone)
                .findFirst()
                .orElse(null);
        dto.setListaContatos(contrato.getListaContatos());
        dto.setTelefoneResponsavelPrincipal(telefonePrincipal);
        dto.setDiasAlternados(contrato.isDiasAlternados());
        dto.setAluno(AlunoMapper.toDTO(contrato.getAluno()));
        dto.setDiaPagamento(contrato.getDiaPagamento());
        dto.setDataInicio(contrato.getDataInicio());
        dto.setValorPagamento(contrato.getValorPagamento());
        dto.setRessarcimentoEmFeriados(contrato.isRessarcimentoEmFeriados());
        dto.setDiasDasAulas(contrato.getListaDeAulas());

        return dto;
    }

    public static Contrato toEntity(ContratoDTO contratoDto) {
        Contrato contratoEntity = new Contrato();
        contratoEntity.setNomeResponsavel(contratoDto.getNomeResponsavel());
        contratoEntity.setDocumentoResponsavel(contratoDto.getDocumentoResponsavel());
        contratoEntity.setListaDeAulas(contratoDto.getDiasDasAulas());
        contratoEntity.setListaContatos(contratoDto.getListaContatos());
        contratoEntity.setDiasAlternados(contratoDto.isDiasAlternados());
        contratoEntity.setAluno(AlunoMapper.toEntity(contratoDto.getAluno()));
        contratoEntity.setDiaPagamento(contratoDto.getDiaPagamento());
        contratoEntity.setDataInicio(contratoDto.getDataInicio());
        contratoEntity.setValorPagamento(contratoDto.getValorPagamento());
        contratoEntity.setRessarcimentoEmFeriados(contratoDto.isRessarcimentoEmFeriados());


        return contratoEntity;
    }
}
