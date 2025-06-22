package com.Classy.services;

import com.Classy.DTO.ContratoDTO;
import com.Classy.entitys.Contato;
import com.Classy.entitys.Contrato;
import com.Classy.entitys.Materia;
import com.Classy.mappers.ContratoMapper;
import com.Classy.repositorys.ContratoRepository;
import com.Classy.util.EnumSituacoesContrato;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceContrato {

    @Autowired
    private ContratoRepository contratoRepository;

    @Transactional
    public ContratoDTO cadastrarContrato(ContratoDTO contrato){

        if(contrato.getAluno() == null){
            throw new IllegalArgumentException("O aluno é obrigatório.");
        }

        if(contrato.getSituacao() == null){
            contrato.setSituacao(EnumSituacoesContrato.PENDENTE);
        }

        // converter o DTO para o Entity
        Contrato contratoEntity = ContratoMapper.toEntity(contrato);
        if(contrato.getDiasDasAulas().isEmpty()){
            System.out.println("TEste");
        }

        //salvar no banco
        try{
            return ContratoMapper.toDTO(contratoRepository.save(contratoEntity));
        }catch (Exception e){
            throw new RuntimeException("Erro ao salvar o cadastro: " + e.getMessage(), e);
        }

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

    @Transactional
    public Optional<ContratoDTO> atualizarContrato(Long id, ContratoDTO contratoatualizadoDto){
        return contratoRepository.findById(id).map(contrato -> {
            contratoRepository.save(ContratoMapper.toUpdateEntity(contrato, contratoatualizadoDto));
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
