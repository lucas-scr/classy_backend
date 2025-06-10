package com.Classy.services;

import com.Classy.DTO.MateriaDTO;
import com.Classy.entitys.Materia;
import com.Classy.exception.EntidadeDuplicadaException;
import com.Classy.exception.GlobalExceptionHandler;
import com.Classy.mappers.MateriaMapper;
import com.Classy.repositorys.MateriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaService {

    private MateriaRepository repository;


    public MateriaDTO cadastrarMateria(MateriaDTO materiaDTO){
        Boolean materiaExiste = repository.existsByNome(materiaDTO.getNome());
        if(materiaExiste){
            throw new EntidadeDuplicadaException("materia", "nome", materiaDTO.getNome());
        }
        return MateriaMapper.toDTO(repository.save(MateriaMapper.toEntity(materiaDTO)));

    }

    public List<MateriaDTO> findAll(){

        return repository.findAll().stream()
                .map(MateriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MateriaDTO findById(Long id){
        Materia materia = repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Materia não encontrada."));
        return MateriaMapper.toDTO(materia);
    }

    public Boolean deletarMateria(Long id){
        repository.deleteById(id);
        return true;
    }
}
