package com.Classy.services;

import com.Classy.DTO.MateriaDTO;
import com.Classy.entitys.Materia;
import com.Classy.repositorys.MateriaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MateriaService {

    private MateriaRepository repository;


    public MateriaDTO cadastrarMateria(MateriaDTO materiaDTO){
        Boolean materiaExiste = repository.existsByNome(materiaDTO.getNome());
        if(materiaExiste){
            return
        }

    }

    public Boolean deletarMateria(){
        return true;
    }
}
