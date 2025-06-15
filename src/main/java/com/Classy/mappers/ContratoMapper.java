package com.Classy.mappers;

import com.Classy.DTO.AlunoDTO;
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
        contratoEntity.setNomeResponsavel(contratoDto.getNomeResponsavel());
        contratoEntity.setDocumentoResponsavel(contratoDto.getDocumentoResponsavel());
        contratoEntity.setListaDeAulas(converterDiasDaAulaParaEntity(contratoDto, contratoEntity));
        contratoEntity.setListaContatos(converterContatosParaEntity(contratoDto, contratoEntity));
        contratoEntity.setDiasAlternados(contratoDto.isDiasAlternados());
        contratoEntity.setAluno(aluno);
        contratoEntity.setDiaPagamento(contratoDto.getDiaPagamento());
        contratoEntity.setDataInicio(contratoDto.getDataInicio());
        contratoEntity.setValorPagamento(contratoDto.getValorPagamento());
        contratoEntity.setRessarcimentoEmFeriados(contratoDto.isRessarcimentoEmFeriados());
        contratoEntity.setSituacao(contratoDto.getSituacoesContrato());
        return contratoEntity;
    }

    public static Contrato toUpdateEntity(Contrato entity,ContratoDTO dto) {
        entity.setNomeResponsavel(dto.getNomeResponsavel());
        entity.setDocumentoResponsavel(dto.getDocumentoResponsavel());
        entity.setListaDeAulas(converterDiasDaAulaParaEntity(dto, entity));
        entity.setListaContatos(converterContatosParaEntity(dto, entity));
        entity.setDiasAlternados(dto.isDiasAlternados());
        entity.setDiaPagamento(dto.getDiaPagamento());
        entity.setDataInicio(dto.getDataInicio());
        entity.setValorPagamento(dto.getValorPagamento());
        entity.setRessarcimentoEmFeriados(dto.isRessarcimentoEmFeriados());

        entity.getListaContatos().clear();
        entity.setListaContatos(converterContatosParaAtualizarEntity(dto, entity));
        entity.getListaDeAulas().clear();
        entity.setListaDeAulas(converterDiasDaAulaParaAtualizarEntity(dto, entity));
        entity.setAluno(converterAlunoParaEntity(dto.getAluno()));
        entity.getAluno().setContrato(entity);
        entity.getListaDeAulas().forEach(diasDaAula -> diasDaAula.setContrato(entity));

        return entity;
    }

    private static List<Contato> converterContatosParaAtualizarEntity(ContratoDTO dto, Contrato entity){

       return dto.getListaContatos().stream()
                .map(contatoDTO -> {
                    Contato contato = new Contato();
                    if(contatoDTO.getId() != null){
                        contato.setId(contatoDTO.getId());
                    }
                    contato.setTelefone(contatoDTO.getTelefone());
                    contato.setResponsavel(contatoDTO.getResponsavel());
                    contato.setPrincipal(contatoDTO.getPrincipal());
                    contato.setContrato(entity);
                    return contato;
                })
                .collect(Collectors.toList());
    }

    private static List<Contato> converterContatosParaEntity(ContratoDTO dto, Contrato entity){

        return dto.getListaContatos().stream()
                .map(contatoDTO -> {
                    Contato contato = new Contato();
                    contato.setTelefone(contatoDTO.getTelefone());
                    contato.setResponsavel(contatoDTO.getResponsavel());
                    contato.setPrincipal(contatoDTO.getPrincipal());
                    contato.setContrato(entity);
                    return contato;
                })
                .collect(Collectors.toList());
    }

    private static List<DiasDaAula> converterDiasDaAulaParaAtualizarEntity(ContratoDTO dto, Contrato entity){

        return dto.getDiasDasAulas().stream()
                .map(diaDTO -> {
                    DiasDaAula diaEntity = new DiasDaAula();
                    System.out.println("Id do DTO" + diaDTO.getId());
                    if(diaDTO.getId() != null){
                        System.out.println(diaDTO.getId());
                        diaEntity.setId(diaDTO.getId());
                    }
                    diaEntity.setDiaDaSemana(diaDTO.getDiaSemana());
                    diaEntity.setHorario(diaDTO.getHorario());
                    diaEntity.setContrato(entity);
                    return diaEntity;
                })
                .collect(Collectors.toList());
    }



    private static List<DiasDaAula> converterDiasDaAulaParaEntity(ContratoDTO dto, Contrato entity){

        return dto.getDiasDasAulas().stream()
                .map(diaDTO -> {
                    DiasDaAula diaEntity = new DiasDaAula();
                    diaEntity.setDiaDaSemana(diaDTO.getDiaSemana());
                    diaEntity.setHorario(diaDTO.getHorario());
                    diaEntity.setContrato(entity);
                    return diaEntity;
                })
                .collect(Collectors.toList());
    }

    private static List<ContatoDTO> converterContatosEntityParaDTO (Contrato entity){
        return entity.getListaContatos().stream()
                .map(ContatoMapper::toDTO).toList();
    }

    private static List<DiasDasAulasDTO> converterDiasDasAulasEntityParaDTO(Contrato entity){
        return entity.getListaDeAulas().stream()
                .map(DiasDasAulasMapper::toDTO).toList();
    }


    private static Aluno converterAlunoParaEntity(AlunoDTO dto){
        Aluno aluno = new Aluno();
        if(dto.getId() != null){
            aluno.setId(dto.getId());
        }
        aluno.setNome(dto.getNome());
        aluno.setDataNascimento(dto.getDataNascimento());

        return aluno;
    }

}
