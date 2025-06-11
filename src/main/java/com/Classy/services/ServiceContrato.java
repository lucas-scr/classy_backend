package com.Classy.services;

import com.Classy.DTO.ContratoDTO;
import com.Classy.entitys.Contrato;
import com.Classy.entitys.Materia;
import com.Classy.mappers.ContratoMapper;
import com.Classy.repositorys.ContratoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceContrato {

    @Autowired
    private ContratoRepository contratoRepository;

    public ContratoDTO cadastrarContrato(ContratoDTO contrato){

        // converter o DTO para o Entity
        Contrato contratoEntity = ContratoMapper.toEntity(contrato);

        //salvar no banco
        return ContratoMapper.toDTO(contratoRepository.save(contratoEntity));

    }

    public List<ContratoDTO> listarContratos(){
        return contratoRepository.findAll().stream()
                .map(ContratoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ContratoDTO buscarPorId(Long id){
        Contrato contrato = contratoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Contrato não encontrado."));
        return ContratoMapper.toDTO(contrato);
    }

    public Optional<ContratoDTO> atualizarContrato(Long id, ContratoDTO contratoatualizadoDto){
        return contratoRepository.findById(id).map(contrato -> {
            contratoRepository.save(ContratoMapper.toEntity(contratoatualizadoDto));
           return ContratoMapper.toDTO(contrato);
        });
    }

    public boolean deletarContrato(Long id){
        return contratoRepository.findById(id).map(contrato ->{
            contratoRepository.delete(contrato);
            return true;
        }).orElse(false);
    }
}
