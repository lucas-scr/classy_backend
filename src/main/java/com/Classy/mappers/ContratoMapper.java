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
import java.util.Objects;
import java.util.Optional;
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
        Optional<Contato> contatoPrincipal = contrato.getListaContatos().stream()
                .filter(Contato::getPrincipal)
                .findFirst();
        contatoPrincipal.ifPresent(c -> dto.setTelefoneResponsavelPrincipal(c.getTelefone()));
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
        if(contrato.getHorarioDiasAlternados() != null && contrato.isDiasAlternados()){
            dto.setHorarioDiasAlternados(contrato.getHorarioDiasAlternados());
        }
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
        contratoEntity.setListaContatos(converterContatosParaEntity(contratoDto, contratoEntity));
        contratoEntity.setDiasAlternados(contratoDto.isDiasAlternados());
        contratoEntity.setDiaPagamento(contratoDto.getDiaPagamento());
        contratoEntity.setDataInicio(contratoDto.getDataInicio());
        contratoEntity.setValorPagamento(contratoDto.getValorPagamento());
        contratoEntity.setRessarcimentoEmFeriados(contratoDto.isRessarcimentoEmFeriados());
        contratoEntity.setAutorizaUsoDeImagem(contratoDto.getAutorizaUsoDeImagem());
        contratoEntity.setSituacao(contratoDto.getSituacao());
        if(contratoDto.getHorarioDiasAlternados() != null && contratoDto.isDiasAlternados()){
            contratoEntity.setHorarioDiasAlternados(contratoDto.getHorarioDiasAlternados());
        }
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
        entity.setAutorizaUsoDeImagem(dto.getAutorizaUsoDeImagem());
        entity.getListaContatos().clear();
        entity.setListaContatos(converterContatosParaEntity(dto, entity));
        entity.getListaDeAulas().clear();
        entity.setListaDeAulas(converterDiasDaAulaParaEntity(dto, entity));
        entity.setAluno(AlunoMapper.toEntity(dto.getAluno()));
        entity.getAluno().setContrato(entity);
        if(dto.getHorarioDiasAlternados() != null && dto.isDiasAlternados()){
            entity.setHorarioDiasAlternados(dto.getHorarioDiasAlternados());
        }
        return entity;
    }

    private static List<Contato> converterContatosParaEntity(ContratoDTO dto, Contrato entity){

        if(dto.getListaContatos().isEmpty()){
            ContatoDTO contatoPrincipal = new ContatoDTO();
            contatoPrincipal.setResponsavel(dto.getNomeResponsavel());
            contatoPrincipal.setTelefone(dto.getTelefoneResponsavelPrincipal());
            dto.getListaContatos().add(contatoPrincipal);
            System.out.println("Entro no cadastro de contato principal");
        }

       return dto.getListaContatos().stream()
                .map(contatoDTO -> {
                    Contato contato = ContatoMapper.toEntity(contatoDTO);
                    contato.setContrato(entity);
                    if(Objects.equals(contato.getTelefone(), dto.getTelefoneResponsavelPrincipal())){
                        contato.setPrincipal(true);
                    }
                    return contato;
                })
                .collect(Collectors.toList());
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

    private static List<ContatoDTO> converterContatosEntityParaDTO (Contrato entity){
        return entity.getListaContatos().stream()
                .map(ContatoMapper::toDTO).toList();
    }

    private static List<DiasDasAulasDTO> converterDiasDasAulasEntityParaDTO(Contrato entity){
        return entity.getListaDeAulas().stream()
                .map(DiasDasAulasMapper::toDTO).toList();
    }


}
