package com.Classy.services;

import com.Classy.models.Contrato;
import com.Classy.repositorys.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    public Contrato cadastrarContrato(Contrato contrato){
        return contratoRepository.save(contrato);
    }

    public List<Contrato> listarContratos(){
        return contratoRepository.findAll();
    }

    public Optional<Contrato> buscarPorId(Long id){
        return contratoRepository.findById(id);
    }

    public Optional<Contrato> atualizarContrato(Long id, Contrato contratoatualizado){
        return contratoRepository.findById(id).map(contrato -> {
           return contratoRepository.save(contrato);
        });
    }

    public boolean deletarContrato(Long id){
        return contratoRepository.findById(id).map(contrato ->{
            contratoRepository.delete(contrato);
            return true;
        }).orElse(false);
    }
}
