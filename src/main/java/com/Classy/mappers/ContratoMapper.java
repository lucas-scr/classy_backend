package com.Classy.mappers;

import com.Classy.DTO.ContratoDTO;
import com.Classy.entitys.Contrato;

public class ContratoMapper {

    public static ContratoDTO toDTO(Contrato contrato){
        ContratoDTO dto = new ContratoDTO();
        dto.setId(contrato.getId());
        dto.setNomeResponsavel(contrato.getNomeResponsavel());
        dto.setDocumentoResponsavel(contrato.getDocumentoResponsavel());
        String telefonePrincipal = contrato.getTelefoneResponsavel().stream()
                .filter(Contato::getPrincipal)
                .map(Contato::getNumero)
                .findFirst()
                .orElse(null);
        dto.setListaContatos(contrato.getListaContatos());
        dto.setTelefoneResponsavelPrincipal(telefonePrincipal);
        dto.setDiasAlternados(contrato.isDiasAlternados());
        dto.setNomeAluno(contrato.getAluno().getNome());
        dto.setDiaPagamento(contrato.getDiaPagamento());
        dto.setDataInicio(contrato.getDataInicio());
        dto.setValorPagamento(contrato.getValorPagamento());
        dto.setRessarcimentoEmFeriados(contrato.isRessarcimentoEmFeriados());
        dto.setDiasDasAulas(contrato.getDiasDasAulas());

        return dto;
    }

    public static Contrato toEntity(ContratoDTO contratoDto) {

        Contrato contratoEntity = new Contrato();
        contratoEntity.setNomeResponsavel(contratoDto.getNomeResponsavel());
        contratoEntity.setDocumentoResponsavel(contratoDto.getDocumentoResponsavel());
        contratoEntity.setTelefoneResponsavel(contratoDto.getTelefoneResponsavelPrincipal());

        return new Contrato();
    }
}
