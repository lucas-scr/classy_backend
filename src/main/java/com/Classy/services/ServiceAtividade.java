package com.Classy.services;

import com.Classy.DTO.AtividadeDTO;
import com.Classy.DTO.ContratoDTO;
import com.Classy.DTO.MateriaDTO;
import com.Classy.entitys.Atividade;
import com.Classy.entitys.Materia;
import com.Classy.exception.EntidadeDuplicadaException;
import com.Classy.mappers.AtividadeMapper;
import com.Classy.mappers.MateriaMapper;
import com.Classy.repositorys.AtividadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceAtividade {

    @Autowired
    private AtividadeRepository repository;

    public AtividadeDTO cadastrarAtividade(AtividadeDTO atividadeDTO){
        if(repository.existsByCodigo(atividadeDTO.getCodigo())){
            throw new EntidadeDuplicadaException("Atividade","código", atividadeDTO.getCodigo());
        }
        Atividade atividade = AtividadeMapper.toEntity(atividadeDTO);
        return AtividadeMapper.toDTO(repository.save(atividade));
    }

    public Optional<AtividadeDTO> atualizarAtividade(Long id, AtividadeDTO atividadeDTO){
        return repository.findById(id).map(
                atividade -> {
                    repository.save(AtividadeMapper.toEntity(atividadeDTO));
                    return AtividadeMapper.toDTO(atividade);
                }
        );
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
       if(repository.existsById(id)){
           throw new EntityNotFoundException("Atividade não encontrada.");
       }
        repository.deleteById(id);
    }
}
