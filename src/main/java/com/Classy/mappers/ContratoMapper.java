package com.Classy.mappers;

import com.Classy.DTO.ContatoDTO;
import com.Classy.DTO.ContratoDTO;
import com.Classy.DTO.DiasDasAulasDTO;
import com.Classy.entitys.Aluno;
import com.Classy.entitys.Contato;
import com.Classy.entitys.Contrato;
import com.Classy.entitys.DiasDaAula;

import java.util.List;
import java.util.stream.Collectors;


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


        dto.setListaContatos(converterContatosEntityParaDTO(contrato));
        dto.setTelefoneResponsavelPrincipal(telefonePrincipal);
        dto.setDiasAlternados(contrato.isDiasAlternados());
        dto.setAluno(AlunoMapper.toDTO(contrato.getAluno()));
        dto.setDiaPagamento(contrato.getDiaPagamento());
        dto.setDataInicio(contrato.getDataInicio());
        dto.setValorPagamento(contrato.getValorPagamento());
        dto.setRessarcimentoEmFeriados(contrato.isRessarcimentoEmFeriados());

        dto.setDiasDasAulas(converterDiasDasAulasEntityParaDTO(contrato));

        dto.setDataCriacao(contrato.getDataCriacao());
        return dto;
    }

    public static Contrato toEntity(ContratoDTO contratoDto) {
        Contrato contratoEntity = new Contrato();
        Aluno aluno = new Aluno();

        aluno.setNome(contratoDto.getAluno().getNome());
        aluno.setDataNascimento(contratoDto.getAluno().getDataNascimento());
        aluno.setContrato(contratoEntity);


        List<Contato> contatos = contratoDto.getListaContatos().stream()
                .map(contatoDTO -> {
                    Contato contato = new Contato();
                    contato.setTelefone(contatoDTO.getTelefone());
                    contato.setResponsavel(contatoDTO.getResponsavel());
                    contato.setPrincipal(contatoDTO.getPrincipal());

                    contato.setContrato(contratoEntity);
                    return contato;
                })
                .collect(Collectors.toList());

        contratoEntity.setNomeResponsavel(contratoDto.getNomeResponsavel());
        contratoEntity.setDocumentoResponsavel(contratoDto.getDocumentoResponsavel());

        List<DiasDaAula> diasDasAulas = contratoDto.getDiasDasAulas().stream()
                        .map(diaDTO -> {
                            DiasDaAula diaEntity = new DiasDaAula();
                            System.out.println(diaDTO);
                            diaEntity.setDiaDaSemana(diaDTO.getDiaSemana());
                            diaEntity.setHorario(diaDTO.getHorario());
                            diaEntity.setContrato(contratoEntity);
                            return diaEntity;
                        })
                .collect(Collectors.toList());

        contratoEntity.setListaDeAulas(diasDasAulas);

        contratoEntity.setListaContatos(contatos);
        contratoEntity.setDiasAlternados(contratoDto.isDiasAlternados());
        contratoEntity.setAluno(aluno);
        contratoEntity.setDiaPagamento(contratoDto.getDiaPagamento());
        contratoEntity.setDataInicio(contratoDto.getDataInicio());
        contratoEntity.setValorPagamento(contratoDto.getValorPagamento());
        contratoEntity.setRessarcimentoEmFeriados(contratoDto.isRessarcimentoEmFeriados());
        contratoEntity.setSituacoesContrato(contratoDto.getSituacoesContrato());
        return contratoEntity;
    }


    private static List<Contato> converterContatosDTOParaEntity (ContratoDTO dto, Contrato entity){
        return dto.getListaContatos().stream()
                .map(contatoDTO -> {
                    Contato novoContato = new Contato();
                    novoContato.setPrincipal(contatoDTO.getPrincipal());
                    novoContato.setContrato(entity);
                    novoContato.setTelefone(contatoDTO.getTelefone());
                    novoContato.setResponsavel(contatoDTO.getResponsavel());
                    return novoContato;
                }).toList();
    }

    private static List<ContatoDTO> converterContatosEntityParaDTO (Contrato entity){
        return entity.getListaContatos().stream()
                .map(ContatoMapper::toDTO).toList();
    }

    private static List<DiasDasAulasDTO> converterDiasDasAulasEntityParaDTO(Contrato entity){
        return entity.getListaDeAulas().stream()
                .map(DiasDasAulasMapper::toDTO).toList();
    }

}
