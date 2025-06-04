package com.Classy.mappers;

import com.Classy.DTO.ContratoDTO;
import com.Classy.entitys.Contrato;

public class ContratoMapper {

    public static ContratoDTO toDTO(Contrato contrato){
        ContratoDTO dto = new ContratoDTO();
        dto.setId(contrato.getId());
        dto.setNomeResponsavel(contrato.getResponsavel());
        dto.setDocumentoResponsavel(contrato.getDocumentoResponsavel());
        String telefonePrincipal = contrato.getTelefoneResponsavel().stream()
                .filter(Contato::getPrincipal)
                .map(Contato::getNumero)
                .findFirst()
                .orElse(null);
        dto.setTelefoneResponsavelPrincipal(telefonePrincipal);
        dto.setDiasAlternados(contrato.getIsDiasAlternados());
        dto.setNomeAluno(contrato.getNomeAluno());
        dto.setDataPagamento(contrato.getDataPagamento());
        dto.setDataInicio(contrato.getDataInicio());
        dto.setValorPagamento(contrato.getValorPagamento());
        dto.setRessarcimentoEmFeriados(contrato.getRessarcimentoEmFeriados());
        dto.setDiasDasAulas(contrato.getDiasDasAulas());

        return dto;
    }

    public static Contrato toEntity(ContratoDTO contratoDto) {

        return new Contrato();
    }
}
