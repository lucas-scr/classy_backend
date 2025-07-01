package com.Classy.services;

import com.Classy.DTO.AtividadeDTO;
import com.Classy.entitys.Atividade;
import com.Classy.exception.EntidadeDuplicadaException;
import com.Classy.exception.GlobalExceptionHandler;
import com.Classy.exception.RequisicaoInvalidaException;
import com.Classy.mappers.AtividadeMapper;
import com.Classy.repositorys.AtividadeRepository;
import com.Classy.util.ArquivoProcessador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceAtividade {

    @Autowired
    private AtividadeRepository repository;

    public AtividadeDTO cadastrarAtividade(AtividadeDTO atividadeDTO){
       this.validarDuplicidadePorCodigo(atividadeDTO.getCodigo());
       if(atividadeDTO.getArquivo() == null && atividadeDTO.getUrl() == null){
           throw new RequisicaoInvalidaException("Informe um arquivo ou uma URL.");
       }
       Atividade atividade = AtividadeMapper.toEntity(atividadeDTO);
        return AtividadeMapper.toDTO(repository.save(atividade));
    }

    public AtividadeDTO atualizarAtividade(Long id, AtividadeDTO atividadeDTO){
        Atividade atividade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException ("Atividade com ID " + id + " não encontrada"));
        if(atividadeDTO.getArquivo() == null && atividadeDTO.getUrl() == null){
            throw new RequisicaoInvalidaException("Informe um arquivo ou uma URL.");
        }
        AtividadeMapper.updateEntityFromDTO(atividadeDTO, atividade);
        return AtividadeMapper.toDTO(repository.save(atividade));
    }

    public List<AtividadeDTO> findAll(){
        return repository.findAll().stream()
                .map(AtividadeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AtividadeDTO findById(Long id){
        Atividade atividade = repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Atividade não encontrada."));
        return AtividadeMapper.toDTO(atividade);
    }

    public void deletarAtividade(Long id){
        repository.findById(id).map(atividade -> {
            repository.delete(atividade);
            return true;
        }).orElse(false);;

    }

    private void validarDuplicidadePorCodigo(String codigo){
        if(repository.existsByCodigo(codigo)){
            throw new EntidadeDuplicadaException("Atividade","código", codigo);
        }
    }
}
