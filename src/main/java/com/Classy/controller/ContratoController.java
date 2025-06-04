package com.Classy.controller;

import com.Classy.models.Contrato;
import com.Classy.repositorys.ContratoRepository;
import com.Classy.services.ContratoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contratos")
@CrossOrigin(origins = "http://localhost:4200")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    // Cadastrar contrato
    @PostMapping
    public Contrato cadastrarContrato(@Valid @RequestBody Contrato contrato){
        return contratoService.cadastrarContrato(contrato);
    }

    @GetMapping
    public ResponseEntity<List<Contrato>> buscarContrato(){
        List<Contrato> contratos = contratoService.listarContratos();
        return ResponseEntity.ok(contratos);
    }

    // Consultar contrato por id
    @GetMapping("/{id}")
    public ResponseEntity<Contrato> buscarContrato(@PathVariable Long id){
        return contratoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Atualizar contrato
    @PutMapping("/{id}")
    public ResponseEntity<Contrato> atualizarContrato(@PathVariable Long id, @Valid @RequestBody Contrato contratoAtualizado){
        return contratoService.atualizarContrato(id, contratoAtualizado).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Deletar contrato
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContrato(@PathVariable Long id){
      if(contratoService.deletarContrato(id)){
          return ResponseEntity.noContent().build();
      }
      return ResponseEntity.notFound().build();
    }



}
