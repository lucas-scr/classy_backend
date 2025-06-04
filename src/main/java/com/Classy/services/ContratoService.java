package com.Classy.services;

import com.Classy.DTO.ContratoDTO;
import com.Classy.entitys.Contrato;
import com.Classy.mappers.ContratoMapper;
import com.Classy.repositorys.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    public ContratoDTO cadastrarContrato(ContratoDTO contrato){

        // converter o DTO para o Entity


        //salvar no banco
        return ContratoMapper.toDTO(contratoRepository.save(contrato));

    }

    public List<ContratoDTO> listarContratos(){
        return contratoRepository.findAll().stream()
                .map(ContratoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ContratoDTO> buscarPorId(Long id){
        return contratoRepository.findById(id).map(ContratoMapper::toDTO);
    }

    public Optional<ContratoDTO> atualizarContrato(Long id, ContratoDTO contratoatualizadoDto){
        return contratoRepository.findById(id).map(contrato -> {
            contratoRepository.save(contrato);
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
